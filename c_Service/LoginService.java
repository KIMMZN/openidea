package c_Service;

import java.util.ArrayList;
import java.util.Scanner;

import c_Client_DAO.Client_DAO;
import c_Client_DAO.Client_DBdao;
import c_Client_DTO.Client_DTO;

public class LoginService {
	Client_DBdao ctdbdao = null;
	
	
	
	public LoginService() {
		init();
		menu();
		
	}
	
	private void init() {
		if(ctdbdao == null) {
			ctdbdao = Client_DAO.getInstance();
		}
	}
	
	public void menu() {
		System.out.println("Computer World");
			Scanner in = new Scanner(System.in);
			boolean flag = true;
			while(flag) {
				System.out.println("1.로그인 3.관리자로그인 4.종료");
				int selnum = in.nextInt();
				in.nextLine();
				switch(selnum) {
					case 1: clientadd(); break;
					case 2: clientList(); break;
					case 3: AdminLoginService admlogin = new AdminLoginService();
					case 4: flag = false; break;
					//case 2: ideaDel(); break;
					//case 3: wordmod(); break;
					
					//case 5: ideaSearch(); break;
					// 6:	flag = false; break;
				}
			}
	}
	
	public void clientadd() {
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
		
		Client_DTO ctdto = new Client_DTO();
		ctdto.setID(ID);
		ctdto.setPassWord(pass);
		ctdto.setName(name);
		ctdto.setPhoneNumber(pnumber);
		ctdto.setAddress(address);
		ctdbdao.add(ctdto);
		//worddao.insert(worddto);
	}
	public void clientList() {
		ArrayList<Client_DTO> clist = ctdbdao.listAll();
		for(Client_DTO cl : clist) {
			System.out.println(cl.toString());
		}
	}
	
	
}
