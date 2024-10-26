package c_Products_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import c_Products_DTO.Products_DTO;
import c_Products_DTO.Products_DTO.ProductType;

public class Products_DAO implements Products_DBdao {
	private String username= "system";
	private String password= "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName= "oracle.jdbc.driver.OracleDriver";
	private Connection con = null; // 커넥션 자원 변수
	
	public Products_DAO() {
		init();
	}
	
	private void init() {	// 드라이버 로드	
		try {
			Class.forName(driverName);
			System.out.println("드라이버 로드");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean con() { // 커넥션 
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("커넥션 자원 획득");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void add(Products_DTO pddto) {
		// TODO Auto-generated method stub
		if(con()) {
			try {
				String sql = "insert into products values" 
							+"(product_num.nextval, ?,?,?,?,?,?,default,?)";
				PreparedStatement psmt = con.prepareStatement(sql);
				//psmt.setInt(1, pddto.getNum());
				psmt.setString(1, pddto.getDelivery_Company());
				psmt.setString(2, pddto.getType().name());
				psmt.setString(3, pddto.getName());
				psmt.setString(4, pddto.getInfo());
				psmt.setInt(5, pddto.getQuantity());
				psmt.setInt(6, pddto.getPrice());
				psmt.setString(7, pddto.getId());
				//psmt.setTimestamp(5, pddto.getIndate());;
				//쿼리실행 값(횟수) 리턴
				int resultInt = psmt.executeUpdate();
				//트랜잭션
				if(resultInt > 0) {
					con.commit();
					System.out.println("등록완료");
				}else {
					con.rollback();
					System.out.println("등록실패");
				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("커넥션 실패");
		}
	}

	@Override
	public ArrayList<Products_DTO> listAll(String idtemp) {
		// TODO Auto-generated method stub
		 ArrayList<Products_DTO> pdlist = new  ArrayList<>();
		if(con()) {
			try {
				String sql = "select * from products where id = ?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, idtemp);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Products_DTO pdto = new Products_DTO();
					pdto.setId(rs.getString("id"));
					pdto.setNum(rs.getInt("num"));
					pdto.setDelivery_Company(rs.getString("Delivery_company"));
					pdto.setType(ProductType.valueOf(rs.getString("type")));
					//pdto.setType(rs.getString("type"));
					pdto.setName(rs.getString("name"));
					pdto.setInfo(rs.getString("info"));
					pdto.setQuantity(rs.getInt("quantity"));
					pdto.setPrice(rs.getInt("price"));
					pdto.setIndate(rs.getTimestamp("indate"));
					pdlist.add(pdto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("실패");
		}
		return pdlist;
	}

	@Override
	public ArrayList<Products_DTO> searchOne(String temp, String idTemp) {
		// TODO Auto-generated method stub
		ArrayList<Products_DTO> pdlist = new  ArrayList<>();
		if(con()) {
			try {
				String sql = "select * from products where "+
							 "id = '"+idTemp+"' and " +	
							 "name like '%"+temp+"%' or "+
							 "Delivery_company like '%"+temp+"%' or "+
							 "type like '%"+temp+"%'";
			
				//con.prepareStatement(sql);
				PreparedStatement psmt =null;
				psmt = con.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Products_DTO pdto = new Products_DTO();
					pdto.setId(rs.getString("id"));
					pdto.setNum(rs.getInt("num"));
					pdto.setDelivery_Company(rs.getString("Delivery_company"));
					pdto.setType(ProductType.valueOf(rs.getString("type")));
					pdto.setName(rs.getString("name"));
					pdto.setInfo(rs.getString("info"));
					pdto.setQuantity(rs.getInt("quantity"));
					pdto.setPrice(rs.getInt("price"));
					pdto.setIndate(rs.getTimestamp("indate"));
					pdlist.add(pdto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("실패");
		}
		return pdlist;
	}

	@Override
	public void delete(int temp) {
		// TODO Auto-generated method stub
		if(con()) {
				try {
					String sql = "delete from products where num = ?";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setInt(1, temp);
					//psmt.setString(1, temp);
					int resultInt = psmt.executeUpdate();
					
					if(resultInt > 0) {
						con.commit();
						System.out.println("삭제");
					}else {
						con.rollback();
						System.out.println("삭제실패");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(con != null) {
							con.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}else {
			System.out.println("실패");
		}
	}

	@Override
	public void update(Products_DTO pddto) {
		// TODO Auto-generated method stub
		if(con()) {
			try {
				String sql = "update products set"+
							 " Delivery_company=?,type=?,name=?,info=?,quantity=?,price=? "
							 + "where num=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(7, pddto.getNum());
				psmt.setString(1, pddto.getDelivery_Company());
				psmt.setString(2, pddto.getType().name());
				psmt.setString(3, pddto.getName());
				psmt.setString(4, pddto.getInfo());
				psmt.setInt(5, pddto.getQuantity());
				psmt.setInt(6, pddto.getPrice());
				int upint = psmt.executeUpdate();
				if(upint > 0) {
					con.commit();
					System.out.println("업데이트완료");
				}else {
					con.rollback();
					System.out.println("업데이트실패");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("실패");
		}
	}

	@Override
	public Products_DTO selectOne(int temp) {
		if(con()) {
			try {
				String sql = "select * from products where num=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(1, temp);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Products_DTO pdto = new Products_DTO();
					pdto.setNum(rs.getInt("num"));
					pdto.setId(rs.getString("id"));
					//pdto.setNum(rs.getInt("num"));
					pdto.setDelivery_Company(rs.getString("Delivery_company"));
					pdto.setType(ProductType.valueOf(rs.getString("type")));
					pdto.setName(rs.getString("name"));
					pdto.setInfo(rs.getString("info"));
					pdto.setQuantity(rs.getInt("quantity"));
					pdto.setPrice(rs.getInt("price")); //개당
					pdto.setIndate(rs.getTimestamp("indate"));
					return pdto;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("실패");
		}
		
		return null;
	}

	@Override
	public ArrayList<Products_DTO> listAllbyClient() {
		// TODO Auto-generated method stub
				 ArrayList<Products_DTO> pdlist = new  ArrayList<>();
				if(con()) {
					try {
						String sql = "select * from products";
						PreparedStatement psmt = con.prepareStatement(sql);
						//psmt.setString(1, idtemp);
						ResultSet rs = psmt.executeQuery();
						while(rs.next()) {
							Products_DTO pdto = new Products_DTO();
							pdto.setId(rs.getString("id"));
							pdto.setNum(rs.getInt("num"));
							pdto.setDelivery_Company(rs.getString("Delivery_company"));
							pdto.setType(ProductType.valueOf(rs.getString("type")));
							//pdto.setType(rs.getString("type"));
							pdto.setName(rs.getString("name"));
							pdto.setInfo(rs.getString("info"));
							pdto.setQuantity(rs.getInt("quantity"));
							pdto.setPrice(rs.getInt("price"));
							pdto.setIndate(rs.getTimestamp("indate"));
							pdlist.add(pdto);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							if(con != null) {
								con.close();
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else {
					System.out.println("실패");
				}
				return pdlist;
				
	}

	@Override
	public ArrayList<Products_DTO> searchOnebyClient(String temp) {
		// TODO Auto-generated method stub
				ArrayList<Products_DTO> pdlist = new ArrayList<>();
				if(con()) {
					try {
						 String sql = "select * from products WHERE " +
		                         "name like ? OR " +
		                         "type like ?";

				            PreparedStatement psmt = con.prepareStatement(sql);
				            String searchTemp = "%" + temp + "%";
				            psmt.setString(1, searchTemp);
				            psmt.setString(2, searchTemp);
				            
						ResultSet rs = psmt.executeQuery();
						while(rs.next()) {
							Products_DTO pdto = new Products_DTO();
							pdto.setId(rs.getString("id"));
							pdto.setNum(rs.getInt("num"));
							pdto.setDelivery_Company(rs.getString("Delivery_company"));
							pdto.setType(ProductType.valueOf(rs.getString("type")));
							pdto.setName(rs.getString("name"));
							pdto.setInfo(rs.getString("info"));
							pdto.setQuantity(rs.getInt("quantity"));
							pdto.setPrice(rs.getInt("price"));
							pdto.setIndate(rs.getTimestamp("indate"));
							pdlist.add(pdto);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							if(con != null) {
								con.close();
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else {
					System.out.println("실패");
				}
				return pdlist;
	}

	
	

	
	
}

	

