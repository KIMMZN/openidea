package c_Service;

import java.util.ArrayList;
import java.util.Scanner;

import c_Products_DAO.Products_DBdao;
import c_Products_DTO.Products_DTO;
import c_Products_DTO.Products_DTO.ProductType;

public class Product_Service {
	Products_DBdao pdbdao = null;
	
	public Product_Service(Products_DBdao pdbdao) {
		this.pdbdao = pdbdao;
		menu();
		
	}
	
	
	public void menu() {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1.상품등록 2.전체보기 3.검색 4.삭제 5.수정 6.수정");
			int selnum = in.nextInt();
			in.nextLine();
			switch(selnum) {
				case 1: product_Add(); break;
				case 2: listAll(); break;
				case 3: searchOne(); break;
				case 4: delete(); break;
				case 5: update(); break;
				//case 5: mod(); break;
				
				//case 3: AdminLoginService admlogin = new AdminLoginService();
				case 6: flag = false; break;
				//case 2: ideaDel(); break;
				//case 3: wordmod(); break;
				
				//case 5: ideaSearch(); break;
				// 6:	flag = false; break;
			}
		}
	}
	public void product_Add() {
		System.out.println("입고");
		Scanner in = new Scanner(System.in);
		System.out.println("입고회사명 입력");
		String comTemp = in.nextLine();
		System.out.println("상품 종류 입력");
		String typeTemp = in.nextLine();
		System.out.println("상품 이름 입력");
		String nameTemp = in.nextLine();
		System.out.println("상품 설명 입력");
		String infoTemp = in.nextLine();
		System.out.println("상품 수량 입력");
		int quantityTemp = in.nextInt();
		in.nextLine();
		System.out.println("상품가격입력(개당)");
		int priceTemp = in.nextInt();
		in.nextLine();
		Products_DTO pddto = new Products_DTO();
		pddto.setDelivery_Company(comTemp);
		pddto.setType(ProductType.valueOf(typeTemp));
		pddto.setName(nameTemp);
		pddto.setInfo(infoTemp);
		pddto.setQuantity(quantityTemp);
		pddto.setPrice(priceTemp);
		pdbdao.add(pddto);
		System.out.println("상품등록완료");
	}
	private void listAll() {
		String temp = null;
		ArrayList<Products_DTO> plist = pdbdao.listAll(temp); //임시방편 수정
		for(Products_DTO pl : plist) {
			System.out.println(pl.toString());
			
		}
	}
	
	private void searchOne() {
		Scanner in = new Scanner(System.in);
		System.out.println("검색어를 입력 하세요");
		String temp = in.nextLine();
		String temp1 = in.nextLine();
		ArrayList<Products_DTO> plist = pdbdao.searchOne(temp,  temp1);
		for(Products_DTO pp : plist) {
			System.out.println(pp.toString());
		}
	}
	/*
	private void delete() {
		listAll();
		Scanner in = new Scanner(System.in);
		System.out.println("삭제할 제품의 이름을 입력하시오");
		String temp = in.nextLine();
		//
		pdbdao.delete(temp);
	}
	*/
	private void delete() {
		listAll();
		Scanner in = new Scanner(System.in);
		System.out.println("삭제할 제품의 넘버를 입력하시오");
		int temp = in.nextInt();
		in.nextLine();
		//
		pdbdao.delete(temp);
	}
	
	
	
	private void update() {
		//public void update(Products_DTO pddto);
		listAll();
		searchOne();
		
		
		Scanner in = new Scanner(System.in);
		//listAll();
		System.out.println("수정할 상품의 번호를 입력하시오");
		int temp = in.nextInt();
		in.nextLine();
		System.out.println("찾은 정보");
		Products_DTO pdto = pdbdao.selectOne(temp);
		System.out.println("입고회사명 입력");
		String comTemp = in.nextLine();
		System.out.println("상품 종류 입력");
		String typeTemp = in.nextLine();
		System.out.println("상품 이름 입력");
		String nameTemp = in.nextLine();
		System.out.println("상품 설명 입력");
		String infoTemp = in.nextLine();
		System.out.println("상품 수량 입력");
		int quantityTemp = in.nextInt();
		in.nextLine();
		System.out.println("상품가격입력(개당)");
		int priceTemp = in.nextInt();
		in.nextLine();
		pdto.setDelivery_Company(comTemp);
		pdto.setType(ProductType.valueOf(typeTemp));
		pdto.setName(nameTemp);
		pdto.setInfo(infoTemp);
		pdto.setQuantity(quantityTemp);
		pdto.setPrice(priceTemp);
		pdbdao.update(pdto);
		System.out.println("수정완료");
		
		
		
		//
		
		
		
		/*System.out.println("입고회사명 입력");
		String comTemp = in.nextLine();
		System.out.println("상품 종류 입력");
		String typeTemp = in.nextLine();
		System.out.println("상품 이름 입력");
		String nameTemp = in.nextLine();
		System.out.println("상품 설명 입력");
		String infoTemp = in.nextLine();
		System.out.println("상품 수량 입력");
		int quantityTemp = in.nextInt();
		in.nextLine();
		System.out.println("상품가격입력(개당)");
		int priceTemp = in.nextInt();
		in.nextLine();
		Products_DTO pddto = new Products_DTO();
		pddto.setDelivery_Company(comTemp);
		pddto.setType(ProductType.valueOf(typeTemp));
		pddto.setName(nameTemp);
		pddto.setInfo(infoTemp);
		pddto.setQuantity(quantityTemp);
		pddto.setPrice(priceTemp);
		pdbdao.update(pddto);
		System.out.println("수정완료");*/
	}
	
	/*
	private void ideaDel() {
		//번호와 제목을 보여준다..
		Scanner in = new Scanner(System.in);
		ideaTitleList();
		//삭제할 번호 선택
		System.out.println("삭제할 번호를 선택하시오");
		int delno = in.nextInt();
		in.nextLine();
		//디비에서 삭제
		ideadao.delete(delno);
		
	}
	
	
	*/
	
	
	
}