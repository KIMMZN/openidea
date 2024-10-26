package c_Client_DAO;

import java.util.ArrayList;

import c_Client_DTO.Client_DTO;
//import dto.WordDTO;
import c_Products_DTO.Products_DTO;

public interface Client_DBdao {
	
	public void add(Client_DTO ctdto); //회원가입
	//public ArrayList<WordDTO> selectAll();
	public ArrayList<Client_DTO> listAll();
	public Client_DTO selectOne(String temp); // 설렉트 원 // id중복검사
	public boolean clientLogin(Client_DTO cdto); // 로그인시 트루 폴스 리턴
	
	public ArrayList<Client_DTO> listMyOrder(String userid);

}
