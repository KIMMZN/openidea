package c_Client_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import c_Client_DTO.Client_DTO;

public class Client_DAO implements Client_DBdao {

	private String username= "system";
	private String password= "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName= "oracle.jdbc.driver.OracleDriver";
	private Connection con = null; // 커넥션 자원 변수
	
	//싱글톤 인스턴스
	private static Client_DAO instance = new Client_DAO();
	//싱글톤 패턴을 사용하기 위해 생성자를 접근제어자 private로 설정
	private Client_DAO() {
		
		init();
	}	
	//인스턴스 게터
	public static Client_DAO getInstance() {
		return instance;
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
	public void add(Client_DTO ctdto) {
		// TODO Auto-generated method stub
		if(con()) {
			try {
				String sql = "insert into c_client values" +"(clien_num.nextval, ?,?,?,?,?,default)";
				PreparedStatement psmt = con.prepareStatement(sql);
				
				psmt.setString(1, ctdto.getID());
				psmt.setString(2, ctdto.getPassWord());
				psmt.setString(3, ctdto.getName());
				psmt.setString(4, ctdto.getPhoneNumber());
				psmt.setString(5, ctdto.getAddress());
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
	public ArrayList<Client_DTO> listAll() {
		// TODO Auto-generated method stub
		ArrayList<Client_DTO> clist = new ArrayList<>();
		if(con()) {
			try {
				String sql = "select * from c_client";
				PreparedStatement psmt = con.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Client_DTO cdto = new Client_DTO();
					cdto.setNum(rs.getInt("num"));
					cdto.setID(rs.getString("id"));
					cdto.setName(rs.getString("name"));
					cdto.setPhoneNumber(rs.getString("phonenumber"));
					cdto.setAddress(rs.getString("address"));
					cdto.setIndate(rs.getTimestamp("indate"));
					clist.add(cdto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("실패");
		}
		return clist;
	}
	/*
    if(conn()) {
			try {
				String sql = "select * from wordlove";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					WordDTO iTemp = new WordDTO();					
					iTemp.setNum(rs.getInt("num"));
					iTemp.setKword(rs.getString("kword"));
					iTemp.setEword(rs.getString("eword"));				
					ilist.add(iTemp);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			System.out.println("실패");
		}
		return ilist;



	*/

	@Override
	public Client_DTO selectOne(String temp) { // id있는지 검사(중복)
		Client_DTO cdto = null;
		if(con()) {
			try {
				String sql = "select * from c_client where id=?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, temp);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					cdto = new Client_DTO();
					 cdto.setID(rs.getString("id"));
		                cdto.setName(rs.getString("name"));
		                cdto.setPhoneNumber(rs.getString("phonenumber"));
		                cdto.setAddress(rs.getString("address"));
		                cdto.setIndate(rs.getTimestamp("indate"));
					System.out.println("debug id " + rs.getString("id"));
					return cdto;
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
		
		return cdto;
	}

	@Override
	public boolean clientLogin(Client_DTO cdto) { //로그인 성공시 boolean타입 반환
		// TODO Auto-generated method stub
		if(con()) {
			try {
				String sql = "select * from c_client where id = ? and password  = ?";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, cdto.getID());
				psmt.setString(2, cdto.getPassWord());
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

	@Override
	public ArrayList<Client_DTO> listMyOrder(String userid) {
		/* // TODO Auto-generated method 
		  String sql = 
		
		
		
		   PreparedStatement pstmt = conn.prepareStatement(query);
		   pstmt.setString(1, clientId); // 사용자의 ID 설정
		   ResultSet rs = pstmt.executeQuery();
		
		
		
		*/
		return null;
	}


	

}
