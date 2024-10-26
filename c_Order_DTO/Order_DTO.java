package c_Order_DTO;

import java.sql.Timestamp;

public class Order_DTO {
	private int order_num = 0;
	//private String adm_id = null;
	private String client_id = null;
	private int product_num = 0;
	private String product_type = null;
	private String product_name = null;
	private int product_quantity =0;
	private int product_price_one = 0;
	private int product_price_total =0;
	private Timestamp indate = null;
	
	
	
	
	

	
	
	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	
	

	//@Override
	//public String toString() {
	//	return "Order_DTO [order_num=" + order_num + ", adm_id=" + adm_id + ", client_id=" + client_id
	//			+ ", product_num=" + product_num + ", product_name=" + product_name + ", product_quantity="
	//			+ product_quantity + ", product_price_one=" + product_price_one + ", product_price_total="
	//			+ product_price_total + ", indate=" + indate + "]";
	//}
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	/*
	public String getAdm_id() {
		return adm_id;
	}
	public void setAdm_id(String adm_id) {
		this.adm_id = adm_id;
	}
	*/
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	public int getProduct_price_one() {
		return product_price_one;
	}
	public void setProduct_price_one(int product_price_one) {
		this.product_price_one = product_price_one;
	}
	public int getProduct_price_total() {
		return product_price_total;
	}
	public void setProduct_price_total(int product_price_total) {
		this.product_price_total = product_price_total;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	
	
	
	
	
	
	
	//product_num number(10) not null,
	//product_name VARCHAR2(40) not null,
	//product_quantity NUMBER(5) not null,
	//product_price_one NUMBER(10) not null,
	//product_price_total NUMBER(10) not null,   
	//indate TIMESTAMP DEFAULT SYSDATE
	

}
