package c_Service;

import java.util.Scanner;

import c_Adm_DAO.Adm_DAO;
import c_Adm_DAO.Adm_DBdao;
import c_Adm_DTO.Adm_DTO;
import c_Products_DAO.Products_DAO;
import c_Products_DAO.Products_DBdao;

public class AdminLogin_Service {
	Adm_DBdao admdbdao = null;
	boolean loginflag = true;
	Products_DBdao pdbdao = null;
	Product_Service pdservice = null;
	
	
	
	
	AdminLogin_Service() {
		init();
		//System.out.println("관리자창");
		menu();
	}
	
	public void init() {
		if(admdbdao == null) {
			admdbdao = new Adm_DAO();
		}
		if(pdbdao == null) {
			pdbdao = new Products_DAO();
		}
	}
	
	
	public void menu() {
	
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1.로그인 2.회원가입 3.종료");
			int selnum = in.nextInt();
			in.nextLine();
			switch(selnum) {
				case 1: admLogin(); break;
				case 2: admMembership(); break;
				//case 3: AdminLoginService admlogin = new AdminLoginService();
				case 3: flag = false; break;
				//case 2: ideaDel(); break;
				//case 3: wordmod(); break;
				
				//case 5: ideaSearch(); break;
				// 6:	flag = false; break;
			}
		}
	}
	public void admLogin() {
		
		System.out.println("로그인");
		Scanner in = new Scanner(System.in);
		System.out.println("ID를 입력하시오");
		String ID = in.nextLine();
		System.out.println("비밀번호를 입력하시오");
		String pass = in.nextLine();
		Adm_DTO admdto = new Adm_DTO();
		admdto.setID(ID);
		admdto.setPassWord(pass);
		if(admdbdao.admLogin(admdto)) {
			System.out.println("로그인성공");
			admon();
			
		}else {
			System.out.println("로그인실패");
		}
	}
	
	public void admMembership() {
		
			System.out.println("회원가입");
			Scanner in = new Scanner(System.in);
			System.out.println("ID를 입력하시오");
			String ID = in.nextLine();

			System.out.println("비밀번호를 입력하시오");
			String pass = in.nextLine();
			System.out.println("이름 입력하시오");
			String name = in.nextLine();
			System.out.println("폰넘버");
			String pnumber = in.nextLine();
			System.out.println("주소");
			String address = in.nextLine();
			
			Adm_DTO admdto = new Adm_DTO();
			admdto.setID(ID);
			admdto.setPassWord(pass);
			admdto.setName(name);
			admdto.setPhoneNumber(pnumber);
			admdto.setAddress(address);
			admdbdao.add(admdto);
	}
	
	public void admon() {
		if(pdservice == null) {
			pdservice = new Product_Service(pdbdao);
		}
	}
	

}
