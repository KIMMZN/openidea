package c_Adm_DAO;

import c_Adm_DTO.Adm_DTO;

public interface Adm_DBdao {
	
	public void add(Adm_DTO admdto);
	public boolean admLogin(Adm_DTO admdto);

}
