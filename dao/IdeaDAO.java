package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dto.IdeaDTO;

public class IdeaDAO {
	private String username= "system";
	private String password= "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName= "oracle.jdbc.driver.OracleDriver";
	private Connection conn = null; // 커넥션 자원 변수
	
	
	private static IdeaDAO ideadao = null;
	
	private IdeaDAO() {
		init();
	}
	//인스턴스
	public static IdeaDAO getInstance() {
		if(ideadao == null) {
			ideadao = new IdeaDAO();
		}
		return ideadao;
	}

	private void init() {		
		try {
			Class.forName(driverName);
			System.out.println("드라이버 로드");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private boolean conn() { // 커넥션 
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("커넥션 자원 획득");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void add (IdeaDTO ideadto) { //add
		if(conn()) {		
			try {
				String sql = "insert into ideadata values (ideanum.nextval,?,?,?,default)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, ideadto.getTitle());
				psmt.setString(2, ideadto.getText());
				psmt.setString(3, ideadto.getUeserName());
				
				int resultInt = psmt.executeUpdate();
				//트랜잭션 처리
				if(resultInt > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("데이터베이스 개선실패");
		}
	}
	
	public ArrayList<IdeaDTO> selectAll() {
		ArrayList<IdeaDTO> ilist = new ArrayList<>();
		if(conn()) {
			
			try {
				String sql = "select * from ideadata";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					IdeaDTO ideadto = new IdeaDTO();
					ideadto.setIdeaNum(rs.getString("ideanum"));
					ideadto.setUeserName(rs.getString("name"));
					ideadto.setTitle(rs.getString("title"));
					ideadto.setText(rs.getString("text"));
					ideadto.setIndate(rs.getString("indate"));
					ilist.add(ideadto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			System.out.println("실패");
		}
		return ilist;
	}
	

	public void delete(String number) {
	  if(conn()) {
		  try {
			String sql = "delete from ideadata where ideanum = ?";
			  PreparedStatement psmt = conn.prepareStatement(sql);			 
			  	psmt.setString(1, number);
				psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
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
	public ArrayList<IdeaDTO> searchOne(String temp) {
		ArrayList<IdeaDTO> idealist = new ArrayList<>();
		if(conn()) {
			try {
				//String sql = "delete from ideadata where ideanum = ?";			
				String sql = "select * from ideadata where title like ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//mapping
				pstmt.setString(1, "%"+temp+"%");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {//next()메서드는 rs에서 참조하는 테이블에서
								//튜플을 순차적으로 하나씩 접근하는 메서드
					IdeaDTO IdeaTemp = new IdeaDTO();
					//rs.getString("id") rs가 접근한 튜플에서 id컬럼의 값을 가져옴
					IdeaTemp.setIdeaNum(rs.getString("ideanum"));
					IdeaTemp.setTitle(rs.getString("title"));;
					IdeaTemp.setText(rs.getString("text"));
					IdeaTemp.setUeserName(rs.getString("name"));
					IdeaTemp.setIndate(rs.getString("indate"));
					idealist.add(IdeaTemp);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("실패");
		}
		return idealist;
	}
	
	public void updateTitle (IdeaDTO ideadto) {
		if(conn()) {
			try {
				String sql = "update ideadata set title=? where ideanum=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(2, ideadto.getIdeaNum());
				psmt.setString(1, ideadto.getTitle());
				psmt.executeUpdate();
				System.out.println("업데이트 완료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateText (IdeaDTO ideadto) {
		if(conn()) {
			try {
				String sql = "update ideadata set text=? where ideanum=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(2, ideadto.getIdeaNum());
				psmt.setString(1, ideadto.getText());
				psmt.executeUpdate();
				System.out.println("업데이트 완료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
}
