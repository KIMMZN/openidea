package c_Client_DTO;

import java.sql.Timestamp;

public class Client_DTO {
	
	private int num = 0;
	private String ID = null;
	private String passWord = null;
	private String name = null;
	private String phoneNumber = null;
	private String address = null;
	private Timestamp indate = null; 
	
	public Timestamp getIndate() {
		return indate;
	}


	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}


	@Override
	public String toString() {
		return "ClientDTO [num=" + num + ", ID=" + ID + ", name=" + name + ", phonNumber="
				+ phoneNumber + ", address=" + address + ", indate=" + indate + "]";
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
