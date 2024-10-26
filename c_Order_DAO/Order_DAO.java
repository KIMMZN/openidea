package c_Order_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import c_Order_DTO.Order_DTO;

public class Order_DAO implements Order_DBdao {
	private String username= "system";
	private String password= "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName= "oracle.jdbc.driver.OracleDriver";
	private Connection con = null; // 커넥션 자원 변수
	
	public Order_DAO() {
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
	public void add(Order_DTO odto) {
		// TODO Auto-generated method stub
		if(con()) {
			try {
				
				con.setAutoCommit(false);
				//상품재고 불러오기
				String sql2 = "select quantity from products where  num = ? ";
				PreparedStatement psmt2 = con.prepareStatement(sql2);
				psmt2.setInt(1, odto.getProduct_num());
				ResultSet rs = psmt2.executeQuery();
				int nowQuantityp = 0;
				if(rs.next()) {
					nowQuantityp = rs.getInt("quantity");
				}
				
				if(nowQuantityp >= odto.getProduct_quantity()) {
					String sql = "insert into client_order values" 
							+"(order_num.nextval, ?,?,?,?,?,?,?,default)";
					PreparedStatement psmt = con.prepareStatement(sql);
					//psmt.setString(1, odto.getAdm_id());
					psmt.setString(1, odto.getClient_id());
					psmt.setInt(2, odto.getProduct_num());
					psmt.setString(3, odto.getProduct_type());
					psmt.setString(4, odto.getProduct_name());
					psmt.setInt(5, odto.getProduct_quantity());
					psmt.setInt(6, odto.getProduct_price_one());
					psmt.setInt(7, odto.getProduct_price_total());
					int resultInt = psmt.executeUpdate();
					

					if(resultInt > 0) {
						//프로덕트 테이블의 수량감소
						String sql3 = "update products set quantity = ? where num = ? ";
						PreparedStatement psmt3 = con.prepareStatement(sql3);
						int updateQuantity = nowQuantityp - odto.getProduct_quantity();
						psmt3.setInt(1, updateQuantity);
						psmt3.setInt(2, odto.getProduct_num());
						int resultInt2 = psmt3.executeUpdate();
						
						if(resultInt2 > 0) {
							con.commit();
							System.out.println("수량 업데이트 완료");
						}else {
							con.rollback();
							System.out.println("수량 업데이트 실패");
						}	
						
					}else {
						con.rollback();
						System.out.println("수량업데이트실패,롤백");
					}	
				}else {
                    // 주문 등록 실패 시 롤백
                    con.rollback();
                    System.out.println("주문실패,롤백");
                }
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					con.setAutoCommit(true);
					//rJOptionPane.showMessageDialog(null, "등록 되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
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
	public ArrayList<Order_DTO> listAll() { //관리자의 고객주문 검색
		// TODO Auto-generated method stub
		ArrayList<Order_DTO> odlist = new ArrayList<>();
		if(con()) {
			try {
				String sql = "select * from client_order";
				PreparedStatement psmt = con.prepareStatement(sql);
				//psmt.setString(1, clientid);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Order_DTO odto = new Order_DTO();
					odto.setOrder_num(rs.getInt("order_num"));
					//odto.setAdm_id(rs.getString("adm_id"));
					odto.setClient_id(rs.getString("client_id"));
					odto.setProduct_num(rs.getInt("product_num"));
					System.out.println("1");
					odto.setProduct_type(rs.getString("product_type"));
					System.out.println("1+1?");
					odto.setProduct_name(rs.getString("product_name"));
					odto.setProduct_quantity(rs.getInt("product_quantity"));
					odto.setProduct_price_one(rs.getInt("product_price_one"));
					System.out.println("2");
					odto.setProduct_price_total(rs.getInt("product_price_total"));
					odto.setIndate(rs.getTimestamp("indate"));
					odlist.add(odto);
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
		return odlist;
	}



	@Override
	public ArrayList<Order_DTO> searchOne(String temp) {
		// TODO Auto-generated method stub
				ArrayList<Order_DTO> odlist = new ArrayList<>();
				if(con()) {
					try {
						String sql = "select * from client_order where client_id like ? or " +
					             "product_name like ? or product_type like ?";
						
						PreparedStatement psmt = con.prepareStatement(sql);
						psmt.setString(1, "%" + temp + "%");
			            psmt.setString(2, "%" + temp + "%");
			            psmt.setString(3, "%" + temp + "%");
						ResultSet rs = psmt.executeQuery();
						while(rs.next()) {
							Order_DTO odto = new Order_DTO();
							odto.setOrder_num(rs.getInt("order_num"));
							//odto.setAdm_id(rs.getString("adm_id"));
							odto.setClient_id(rs.getString("client_id"));
							odto.setProduct_num(rs.getInt("product_num"));
							System.out.println("1");
							odto.setProduct_type(rs.getString("product_type"));
							System.out.println("1+1?");
							odto.setProduct_name(rs.getString("product_name"));
							odto.setProduct_quantity(rs.getInt("product_quantity"));
							odto.setProduct_price_one(rs.getInt("product_price_one"));
							System.out.println("2");
							odto.setProduct_price_total(rs.getInt("product_price_total"));
							odto.setIndate(rs.getTimestamp("indate"));
							odlist.add(odto);
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
		return odlist;
	}



	@Override
	public boolean add1(Order_DTO odto) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(con()) {
					try {
						
						con.setAutoCommit(false);
						//상품재고 불러오기
						String sql2 = "select quantity from products where  num = ? ";
						PreparedStatement psmt2 = con.prepareStatement(sql2);
						psmt2.setInt(1, odto.getProduct_num());
						ResultSet rs = psmt2.executeQuery();
						int nowQuantityp = 0;
						if(rs.next()) {
							nowQuantityp = rs.getInt("quantity");
						}
						
						if(nowQuantityp >= odto.getProduct_quantity()) {
							String sql = "insert into client_order values" 
									+"(order_num.nextval, ?,?,?,?,?,?,?,default)";
							PreparedStatement psmt = con.prepareStatement(sql);
							//psmt.setString(1, odto.getAdm_id());
							psmt.setString(1, odto.getClient_id());
							psmt.setInt(2, odto.getProduct_num());
							psmt.setString(3, odto.getProduct_type());
							psmt.setString(4, odto.getProduct_name());
							psmt.setInt(5, odto.getProduct_quantity());
							psmt.setInt(6, odto.getProduct_price_one());
							psmt.setInt(7, odto.getProduct_price_total());
							int resultInt = psmt.executeUpdate();
							

							if(resultInt > 0) {
								//프로덕트 테이블의 수량감소
								String sql3 = "update products set quantity = ? where num = ? ";
								PreparedStatement psmt3 = con.prepareStatement(sql3);
								int updateQuantity = nowQuantityp - odto.getProduct_quantity();
								psmt3.setInt(1, updateQuantity);
								psmt3.setInt(2, odto.getProduct_num());
								int resultInt2 = psmt3.executeUpdate();
								
								if(resultInt2 > 0) {
									con.commit();
									System.out.println("수량 업데이트 완료");
									return true;
								}else {
									con.rollback();
									System.out.println("수량 업데이트 실패");
								}	
								
							}else {
								con.rollback();
								System.out.println("수량업데이트실패,롤백");
							}	
						}else {
		                    // 주문 등록 실패 시 롤백
		                    con.rollback();
		                    System.out.println("주문실패,롤백");
		                }
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							con.setAutoCommit(true);
							//rJOptionPane.showMessageDialog(null, "등록 되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
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
				
		return false;
	}



	@Override
	public ArrayList<Order_DTO> listAllbyClient(String idtemp) { // 고객 주문정보 리스트
		ArrayList<Order_DTO> odlist = new ArrayList<>();
		if(con()) {
			try {
				String sql = "select * from client_order where client_id = ?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, idtemp);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Order_DTO odto = new Order_DTO();
					odto.setOrder_num(rs.getInt("order_num"));
					//odto.setAdm_id(rs.getString("adm_id"));
					odto.setClient_id(rs.getString("client_id"));
					odto.setProduct_num(rs.getInt("product_num"));
					System.out.println("1디벅");
					odto.setProduct_type(rs.getString("product_type"));
					System.out.println("1+1디벅");
					odto.setProduct_name(rs.getString("product_name"));
					odto.setProduct_quantity(rs.getInt("product_quantity"));
					odto.setProduct_price_one(rs.getInt("product_price_one"));
					System.out.println("2디벅");
					odto.setProduct_price_total(rs.getInt("product_price_total"));
					odto.setIndate(rs.getTimestamp("indate"));
					odlist.add(odto);
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
		return odlist;
	}
	
	

}
