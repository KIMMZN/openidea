package c_Products_DAO;

import java.util.ArrayList;

import c_Client_DTO.Client_DTO;
import c_Products_DTO.Products_DTO;

public interface Products_DBdao {
	
	public void add(Products_DTO pddto);//상품추가
	
	
	public ArrayList<Products_DTO> listAll(String idtemp); // 관리자가 list 볼때
	public ArrayList<Products_DTO> listAllbyClient();// 고객이 프로덕츠 list 볼때
	public ArrayList<Products_DTO> searchOne(String temp, String idTemps);//관리자가
	public ArrayList<Products_DTO> searchOnebyClient(String temp);
	
	public void delete(int temp);
	
	public void update(Products_DTO pddto);
	
	public Products_DTO selectOne(int temp); //주문번호로 검색시
	
	
	

}
