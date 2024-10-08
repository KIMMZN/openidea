package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

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
	public void add (IdeaDTO ideadto) {
		if(conn()) {		
			try {
				String sql = "insert into ideadata values (?,?,?,default,ideanum.nextval)";
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
	
/*	public void delete(String delId) {
		if(conn()) {
			try {
				String sql = "delete from fishdata where id=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, delId);
				psmt.executeUpdate();
			} catch (Exception e) {
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
	}*/
	public void delete(String number) {
	  if(conn()) {
		  try {
			String sql = "select * from ideadata where ideanum = ? and";;
			  PreparedStatement psmt = conn.prepareStatement(sql);			 
			  	psmt.setString(1, number);
			  	psmt.setstring
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
	
	
	
	
	
	
}
