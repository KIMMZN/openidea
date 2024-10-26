package c_Adm_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import c_Adm_DTO.Adm_DTO;

public class Adm_DAO implements Adm_DBdao {
	
	private String username= "system";
	private String password= "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName= "oracle.jdbc.driver.OracleDriver";
	private Connection con = null; // 커넥션 자원 변수
	
	public Adm_DAO() {
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
	public void add(Adm_DTO admdto) {
		// TODO Auto-generated method stub
		if(con()) {
			try {
				String sql = "insert into c_admin values" +"(clien_num.nextval, ?,?,?,?,?,default)";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, admdto.getID());
				psmt.setString(2, admdto.getPassWord());
				psmt.setString(3, admdto.getName());
				psmt.setString(4, admdto.getPhoneNumber());
				psmt.setString(5, admdto.getAddress());
				//쿼리실행 값(횟수) 리턴
				int resultInt = psmt.executeUpdate();
				//트랜잭션
				if(resultInt > 0) {
					con.commit();
				}else {
					con.rollback();
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
	public boolean admLogin(Adm_DTO admdto) {
		// TODO Auto-generated method stub
		if(con()) {
			try {
				String sql = "SELECT * FROM c_admin WHERE id = ? AND password  = ?";
				PreparedStatement psmt = con.prepareStatement(sql);
				
				psmt.setString(1, admdto.getID());
				psmt.setString(2, admdto.getPassWord());
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					return true;
					
				}else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else {
			System.out.println("실패");
			return false;
		}
		
		
	}



	
	
	
	/*
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
	 */
	/*
	public WordDTO selectOne (int findId) {
		//ArrayList<IdeaDTO> idealist = new ArrayList<>();
		if(conn()) {
			try {
				//String sql = "delete from ideadata where ideanum = ?";			
				String sql = "select * from wordlove where num=?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//mapping
				pstmt.setInt(1, findId);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {//next()메서드는 rs에서 참조하는 테이블에서
								//튜플을 순차적으로 하나씩 접근하는 메서드
					WordDTO iTemp = new WordDTO();
					//rs.getString("id") rs가 접근한 튜플에서 id컬럼의 값을 가져옴
					iTemp.setNum(rs.getInt("num"));
					iTemp.setKword(rs.getString("kword"));
					iTemp.setEword(rs.getString("eword"));	
					return iTemp;
					//idealist.add(iTemp);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("실패");
		}
		return null;
	}
	
	
	
	*/

}
