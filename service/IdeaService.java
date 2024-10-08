package service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.IdeaDAO;
import dto.IdeaDTO;

public class IdeaService {
	IdeaDAO ideadao = IdeaDAO.getInstance();
	
	public void menu() {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("1.제안");
			System.out.println("2.수정");
			System.out.println("3.삭제");
			System.out.println("4.전체보기");
			System.out.println("5.제목으로 검색");
			System.out.println("6.종료");
			int selNum = in.nextInt(); in.nextLine();
			if(selNum ==1 ) {
				System.out.println("1.등록");
				add();
			}else if(selNum ==2) {
				
			}else if(selNum ==3) {
				delete();
			}else if(selNum ==4) {
				System.out.println("전체보기");
				list();
			}else if(selNum ==5) {
				//update();
			}else if(selNum ==6) {
				//break;
			}
		}
	}
	
	private void add() {
		Scanner in = new Scanner(System.in);
		
		IdeaDTO ideadto = new IdeaDTO();
		System.out.println("제목을 입력하시오");
		String tempTitle = in.nextLine();
		System.out.println("설명을 입력하시오");
		String tempText = in.nextLine();
		System.out.println("작성자 이름을 입력하시오");
		String tempUname = in.nextLine();
		
		ideadto.setTitle(tempTitle);
		ideadto.setText(tempText);
		ideadto.setUeserName(tempUname);
		ideadao.add(ideadto);
		System.out.println("입력완료");
	}
	private void list() {
		ArrayList<IdeaDTO> idealist = new ArrayList<>();
		idealist = ideadao.selectAll();
		System.out.println("총 갯수는: " + idealist.size());
		
		for(IdeaDTO ideatemplist : idealist) {
			System.out.println(ideatemplist.toString());
			
		}
	}
	private void delete() {
		ArrayList<IdeaDTO> idealist = new ArrayList<>();
		idealist = ideadao.selectAll();
		System.out.println("총 갯수는: " + idealist.size());
		
		for(IdeaDTO ideatemplist : idealist) {
			System.out.println(ideatemplist.toString2());
			
		}
		System.out.println("삭제할 넘버를 입력하시오");
		Scanner in = new Scanner(System.in);
		String tempNumber = in.nextLine();
		ideadao.delete(tempNumber);
		
		
		
		
		
	}
	
	
}
