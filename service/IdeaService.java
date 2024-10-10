package service;

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
				update();
			}else if(selNum ==3) {
				delete();
			}else if(selNum ==4) {
				System.out.println("전체보기");
				list();
			}else if(selNum ==5) {
				searchOne();
				//update();
			}else if(selNum ==6) {
				break;
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
		temp();
		
		System.out.println("삭제할 넘버를 입력하시오");
		Scanner in = new Scanner(System.in);
		String tempNumber = in.nextLine();
		ideadao.delete(tempNumber);
	}
	private void searchOne() {
		//매개변수 String을 넘겨주고
		//객체어레이리스틀 받아서 사용한다
		ArrayList<IdeaDTO> searchlist = null;
		System.out.println("검색할 단어를 입력하시오");
		Scanner in = new Scanner(System.in);
		String temp = in.nextLine();
		//public ArrayList<IdeaDTO> searchOne(String temp)
		searchlist = ideadao.searchOne(temp);
		for(IdeaDTO slist : searchlist) {
			System.out.println(slist.toString());
		}
	
		
	}
	private void update() {
		temp();
		
		IdeaDTO ideadto = new IdeaDTO();// 매개변수로 넘겨줄 객체
		
		System.out.println("수정 가능한 목록 >> 1. 제목 / 2. 내용");
		Scanner in = new Scanner(System.in);
		int tempn = in.nextInt();
		in.nextLine();
		if(tempn ==1) {
			System.out.println("수정할 제목의 번호를 입력하시오");
			String temptitle = in.nextLine();
			System.out.println("수정할 제목의 내용을 입력하시오");
			String temptitile2 = in.nextLine();
			ideadto.setIdeaNum(temptitle);
			ideadto.setTitle(temptitile2);
			ideadao.updateTitle(ideadto);
			//번호를 찾아서 제목 수정
		}else if(tempn ==2) {
			System.out.println("수정할 본문의 번호를 입력하시오");
			String tempText = in.nextLine();
			System.out.println("수정할 제목의 내용을 입력하시오");
			String tempText2 = in.nextLine();
			ideadto.setIdeaNum(tempText);
			ideadto.setText(tempText2);
			ideadao.updateText(ideadto);
			//번호를 찾아서 내용 수정
		}
	}
	
	private void temp() { // 번호 제목 보여주기 
		ArrayList<IdeaDTO> idealist = new ArrayList<>();
		idealist = ideadao.selectAll();
		System.out.println("총 갯수는: " + idealist.size());
		
		for(IdeaDTO ideatemplist : idealist) {
			System.out.println(ideatemplist.toString2());
			
		}
		
	}
	
	
}
