package c_Client_Service_Frame_Menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import c_Client_DAO.Client_DBdao;
import c_Order_DAO.Order_DAO;
import c_Order_DAO.Order_DBdao;
import c_Order_DTO.Order_DTO;
import c_Products_DAO.Products_DAO;
import c_Products_DAO.Products_DBdao;
import c_Products_DTO.Products_DTO;

public class c_Client_Service_Frame_Menu extends JFrame implements ActionListener,
MouseListener, KeyListener {
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension screenSize = toolkit.getScreenSize(); // 화면크기
	private Order_DBdao odbdao = null;
	private Client_DBdao cdbdao = null;
	private String username= null;
	private Products_DBdao pdbdao = null;
	JPanel northPanel = new JPanel();
	//탭
	JTabbedPane tPane = new JTabbedPane();
	
	  //패널1
	 private JTable myOrderTable;
	 private String colNames[] = {"ID","주문넘버","타입","상품명","수량","개당가격","총가격","주문일"};
	 private DefaultTableModel model = new DefaultTableModel(colNames, 0);
     private JScrollPane scrollPane;
     JTextField searchField;
     
     //중앙 jtable패널
     private DefaultTableModel Modelsearch;
     private JButton searchButton;
     
     //주문 패널
     private JButton orderButton;
     //상품주문패널 - jtable
     private JTable searchTable;
     private int row;
	 private int col;
     JTextField productNumberField;
     JTextField productNameField;
     JTextField quantityField;
     JTextField productPriceField;
     JTextField TotalPriceField;
   
     
     
     //
	 
    public c_Client_Service_Frame_Menu(String username, Client_DBdao cdbdao) {
    	init();
        this.username = username;
        this.cdbdao = cdbdao;
        
        this.setTitle("고객 서비스 프로그램 v.1.0");
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // North Panel 구성
        northPanel.add(new JLabel("나의 정보: " + username));
        this.add(northPanel, "North");
        
        // JTabbedPane에 탭 추가
        // "나의 주문정보" 탭에 JTable 추가
        myOrderTable = new JTable(model); 
        JScrollPane orderScrollPane = new JScrollPane(myOrderTable);
        tPane.addTab("나의 주문정보", orderScrollPane);
        
        // 다른 탭 추가
        tPane.addTab("주문 관리", orderPanel());
        tPane.addMouseListener(this);
        
        // JTabbedPane을 프레임에 추가
        this.add(tPane, "Center");

        // JFrame 설정
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        loadJDB();
    }
    
    private void init() {
    	if(odbdao == null) {
    		odbdao = new Order_DAO();
    	}
    	if(pdbdao == null) {
    		pdbdao = new Products_DAO();
    	}
    	
    	
    }
    
    private void loadJDB() {
    	model.setRowCount(0); // 기존행 초기화
    	//public ArrayList<Client_DTO> listAll();
    	
    	//public ArrayList<Order_DTO> listAllbyClient(String idtemp) 
    	
    	ArrayList<Order_DTO> cdlist = odbdao.listAllbyClient(username);
    	//ArrayList<Client_DTO> cdlist = cdbdao.listMyOrder(username);
		
		for(Order_DTO olist : cdlist) {
			String[] data= {
					
					//olist.getAdm_id(),
					olist.getClient_id(),
					String.valueOf(olist.getOrder_num()),
					olist.getProduct_type().toString(),
					//olist.getClient_id(),
					//String.valueOf(olist.getProduct_num()),
					olist.getProduct_name(),
					String.valueOf(olist.getProduct_quantity()),
					String.valueOf(olist.getProduct_price_one()),
					String.valueOf(olist.getProduct_price_total()),
					olist.getIndate().toString()
			};
			model.addRow(data);
		}
    }
    
    private JPanel orderPanel (){
    	 JPanel mainpanel = new JPanel(new BorderLayout());
    	 
    	 //북쪽, 검색패널 및 필드 버튼
    	 // 검색 패널 추가 (위쪽)
         JPanel searchPanel = new JPanel(new BorderLayout());
         
         searchField = new JTextField();//검색필드
         searchButton = new JButton("검색");
         searchButton.addActionListener(this);
         JLabel searchlabel = new JLabel("상품이름: ");
         
         searchPanel.add(searchlabel, "West");
         searchPanel.add(searchField, BorderLayout.CENTER);
         searchPanel.add(searchButton, "East");
         
         // JTable 추가 (중앙)
         String[] col = { "상품번호", "타입", "상품명", "수량", "개당가격","상품등록일"};
         Modelsearch = new DefaultTableModel(col, 0);
         //
         searchTable = new JTable(Modelsearch);
         searchTable.addMouseListener(this);
         JScrollPane searchScrollP = new JScrollPane(searchTable);
         loadSearchDB();
         
         
         //
         // 주문 패널 추가 (아래쪽)
         
         
         JPanel orderMainPanel = new JPanel(new BorderLayout());
        
         //
        
         
         //
         JPanel orderPanel = new JPanel(new GridLayout(6, 2, 5, 5));
         orderPanel.add(new JLabel("상품 넘버:"));
         productNumberField = new JTextField();
         orderPanel.add(productNumberField);
         
         orderPanel.add(new JLabel("상품 이름:"));
         productNameField = new JTextField();
         orderPanel.add(productNameField);
         
         orderPanel.add(new JLabel("주문 수량:"));
         quantityField = new JTextField();
         orderPanel.add(quantityField);
         
         orderPanel.add(new JLabel("가격"));
         productPriceField = new JTextField();
         orderPanel.add(productPriceField);
         
         orderPanel.add(new JLabel("종합가격"));
         TotalPriceField = new JTextField();
         orderPanel.add(TotalPriceField);
         
         // 가격관련 필드 박스에 키 리스너 추가
      // quantityField.addKeyListener(this);
         //productPriceField.addKeyListener(this);
 		//JTextField TotalPriceField;
         quantityField.addKeyListener(this);
         productPriceField.addKeyListener(this);
         TotalPriceField.addKeyListener(this);
         //주문 버튼
         
         orderButton = new JButton("주문 등록");
         orderPanel.add(orderButton);
         orderButton.addActionListener(this);
         //
         orderMainPanel.add(new JLabel("주문"), BorderLayout.NORTH);
         orderMainPanel.add(orderPanel, BorderLayout.CENTER);
         
         
         

         
         //
         mainpanel.add(orderMainPanel, BorderLayout.SOUTH);
         mainpanel.add(searchScrollP, BorderLayout.CENTER);
    	 mainpanel.add(searchPanel, BorderLayout.NORTH);
    	
    	return mainpanel;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchButton) {
			searchButtonjdb();
		}
		if(e.getSource() == orderButton ) {
			orderButton();
		}
		
	}
	
	private void loadSearchDB() { // 주문탭 jtable db 
		Modelsearch.setRowCount(0); // 기존행 초기화
			//private Products_DBdao pdbdao = null;
		//String[] col = { "상품번호", "상품명", "타입", "수량", "개당가격", "총가격","상품등록일"};
		ArrayList<Products_DTO> pdlist = pdbdao.listAllbyClient();
		
		for(Products_DTO plist : pdlist) {
			String[] data= {
					//plist.getId(),
					String.valueOf(plist.getNum()),
					//plist.getDelivery_Company(),
					plist.getType().toString(),
					plist.getName(),
					//plist.getInfo(),
					String.valueOf(plist.getQuantity()),
					String.valueOf(plist.getPrice()),
					//String.valueOf(plist.getPrice() * plist.getQuantity()), //총가격
					plist.getIndate().toString()
			};
			Modelsearch.addRow(data);
		}
	}
	private void searchButtonjdb() {
		System.out.println("디버그 버튼");
		Modelsearch.setRowCount(0); // 기존행 초기화
		String searhTemp= searchField.getText();
		System.out.println(searhTemp+ " 디버그");
		//String searhTemp1= northSouthField_c.getText();
		ArrayList<Products_DTO> pdlist = pdbdao.searchOnebyClient(searhTemp);
		
		for(Products_DTO plist : pdlist) {
			String[] data= {
					//plist.getId(),
					String.valueOf(plist.getNum()),
					//plist.getDelivery_Company(),
					plist.getType().toString(),
					plist.getName(),
					//plist.getInfo(),
					String.valueOf(plist.getQuantity()),
					String.valueOf(plist.getPrice()),
					String.valueOf(plist.getPrice() * plist.getQuantity()), //총가격
					plist.getIndate().toString()
			};
			Modelsearch.addRow(data);
		}
	}
	private void orderButton() { // 주문탭- 주문버튼클릭시메서드
		Order_DTO odto = new Order_DTO();
		/*
		 JTextField productNumberField;
         JTextField productNameField;
         JTextField quantityField;
         JTextField productPriceField;
		
		*/
		String cid =  username;
		int pdnum = Integer.parseInt(productNumberField.getText());
		odto.setClient_id(cid);
		odto.setProduct_num(pdnum);
		pdbdao.selectOne(pdnum);
		Products_DTO pdto = pdbdao.selectOne(pdnum);
		odto.setProduct_type(pdto.getType().toString());
		odto.setProduct_name(pdto.getName());
		odto.setProduct_quantity(Integer.parseInt(quantityField.getText()));
		odto.setProduct_price_one(pdto.getPrice());
		int oprice = pdto.getPrice();
		int oquantity = Integer.parseInt(quantityField.getText());
		int total = oprice * oquantity;
		
		odto.setProduct_price_total(total);
		
		if(odbdao.add1(odto)) {
			JOptionPane.showMessageDialog(this, "주문완료", "확인", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "주문실패", "실패", JOptionPane.INFORMATION_MESSAGE);
		}
		
		searchButtonjdb(); // 검색테이블 리로드
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchTable) {
			row = searchTable.getSelectedRow();
			col = searchTable.getSelectedColumn();
			
			Object value = searchTable.getValueAt(row, col);
			System.out.println("클릭된 행: " + row + ", 열: " + col + ", 값: "
					+ value);
			productNumberField.setText(searchTable.getValueAt(row, 0).toString());
			productNameField.setText(searchTable.getValueAt(row, 2).toString());
			productPriceField.setText(searchTable.getValueAt(row, 4).toString());
		}
        //TotalPriceField.setText(searchTable.getValueAt(row, col));
        
        int index = tPane.getSelectedIndex();
        
        if(index == tPane.indexOfTab("나의 주문정보")) {
        	loadJDB();
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// quantityField.addKeyListener(this);
        //productPriceField.addKeyListener(this);
		//JTextField TotalPriceField;
		
		if(e.getSource() == quantityField || e.getSource() == productPriceField ) {
			calculateTotal();
		}else if(e.getSource() == TotalPriceField) {
			calculPriceOne();
		}
		
	}
	private void calculateTotal() { // 종합가격 계산
		 try {
		        int priceOne = Integer.parseInt(productPriceField.getText());
		        int totalQuantity = Integer.parseInt(quantityField.getText());

		        if (priceOne > 0 && totalQuantity > 0) {
		            int totalPrice = priceOne * totalQuantity;
		            TotalPriceField.setText(Integer.toString(totalPrice));
		        } else {
		        	TotalPriceField.setText("");
		        }
		    } catch (NumberFormatException e) {
		    	TotalPriceField.setText("");
		    }
	}
	private void calculPriceOne() { //개당가격 계산
	    try {
	        int totalPrice = Integer.parseInt(TotalPriceField.getText());
	        int totalQuantity = Integer.parseInt(quantityField.getText());

	        if (totalQuantity > 0 && totalPrice > 0) {
	            int priceOne = totalPrice / totalQuantity;
	            productPriceField.setText(Integer.toString(priceOne));
	        } else {
	        	productPriceField.setText("");
	        }
	    } catch (NumberFormatException e) {
	        
	    	productPriceField.setText("");
	    }
	}
	
	
	
    
    
}