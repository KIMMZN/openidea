package c_Products_DTO;

import java.sql.Timestamp;

import c_Products_Service_Frame.Products_Service_Frame_Main;
import c_Service_Frame.Adm_Login_Frame2;

public class Products_DTO {
	//Adm_Login_Frame2 alfm2;
	//Products_Service_Frame_Main psfm;
	
	
	//컴퓨터 부품 정보
	public enum ProductType{
		CPU, 그래픽카드, 메인보드, 메모리카드, SSD, HDD, 케이스, 파워, 모니터, 운영체제, 마우스, 키보드, 스피커 
	}
	
	
	//private String type = null;
	private int num = 0;
	private String delivery_Company = null;
	private ProductType type = null;
	private String name = null;
	private String info = null;
	private int quantity = 0;
	private int price = 0;
	private Timestamp indate = null;
	private String id = null;

	@Override
		public String toString() {
		  int totalPrice = (quantity * price);
			
			return "Products_DTO [num=" + num + ", delivery_Company=" + delivery_Company + ","
			+ " type=" + type + ", name="
			+ name + ", info=" + info + ", quantity=" + quantity + ", price(개당)=" + price +
			", totalPrice="+ totalPrice + ", indate=" + indate + "]";
			
		}
	
	 public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}
	
	
	
	public String getDelivery_Company() {
		return delivery_Company;
	}

	public void setDelivery_Company(String delivery_Company) {
		this.delivery_Company = delivery_Company;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public Timestamp getIndate() {
		return indate;
	}

	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}

	//생성자
	public Products_DTO() {};
	
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}
	
	
	
	

}
