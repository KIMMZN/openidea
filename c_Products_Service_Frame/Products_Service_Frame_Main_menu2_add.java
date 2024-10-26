package c_Products_Service_Frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import c_Client_DAO.Client_DAO;
import c_Client_DAO.Client_DBdao;
import c_Client_DTO.Client_DTO;
import c_Order_DAO.Order_DBdao;
import c_Order_DTO.Order_DTO;
import c_Products_DAO.Products_DBdao;
import c_Products_DTO.Products_DTO;

public class Products_Service_Frame_Main_menu2_add extends JFrame implements ActionListener,
			MouseListener, WindowListener {
	private Client_DBdao cltdbdao = null;
	private Products_DBdao pdbdao = null;
	private Order_DBdao odbdao = null;
	private String idTemp;
	private Products_Service_Frame_Main_menu2 psfmm2 = null;
	
	JPanel northSearchPanel = new JPanel();
    JPanel centerInputPanel = new JPanel();
    JPanel southButtonPanel = new JPanel();
    
    
    //north panel;
    
    
    
    //jtable,northpanel;
    private JTextField searchField;
    private JTable jtable;
    JButton searchButton;
   // private DefaultTableModel model;
    private String[] column = {"상품 번호", "상품명", "타입", "수량", "가격"};
    DefaultTableModel model = new DefaultTableModel(column, 0);
    //jtable 로우 컬럼
	private int row; 
	private int col;
    
    
    //private JTable jtable;
	//private String colNames[] = {"등록ID", "넘버","공급사","타입","상품명","정보","수량","개당가격","총가격","등록일"};
	//private DefaultTableModel model = new DefaultTableModel(colNames, 0);
    

    private JTextField quantityField;
    private JTextField clientIdField;
    private JTextField numberField;
    
    //south panel 주문 취소 버튼
    JButton registerButton;
    JButton cancelButton;
	
	Products_Service_Frame_Main_menu2_add(Products_DBdao pdbdao, Order_DBdao odbdao, String idTemp,
			Products_Service_Frame_Main_menu2 psfmm2) {
		System.out.println("cltdbdao: " + cltdbdao);
		init();
		this.pdbdao = pdbdao;
		this.odbdao = odbdao;
		this.idTemp = idTemp;
		this.psfmm2 = psfmm2;
		
		 System.out.println("디버그: 생성자 호출됨");
		    System.out.println("pdbdao: " + this.pdbdao);
		    System.out.println("odbdao: " + this.odbdao);
		    System.out.println("idTemp: " + this.idTemp);
		    System.out.println("psfmm2: " + this.psfmm2);
		    System.out.println("cltdbdao: " + cltdbdao);
		
		this.setTitle("조립 컴퓨터 재고관리 프로그램 v.1.0");
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        
        //northpanel // 검색창
        northSearchPanel.setLayout(new BorderLayout());
        searchField = new JTextField(15);
        searchButton = new JButton("검색");
        northSearchPanel.add(new JLabel("상품 이름: "), BorderLayout.WEST);
        northSearchPanel.add(searchField, BorderLayout.CENTER);
        northSearchPanel.add(searchButton, BorderLayout.EAST);

        //검색버튼 리스너
        searchButton.addActionListener(this);

        // centerpanel - jtable 표시
        centerInputPanel.setLayout(new BorderLayout());
        jtable = new JTable(model);
        //jtable 마우스 리스너 등록;
        jtable.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(jtable);
        centerInputPanel.add(scrollPane, BorderLayout.CENTER);

        // centerpanel //주문 입력 필드
        JPanel orderPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        orderPanel.add(new JLabel("넘버:"));
        numberField = new JTextField();
        orderPanel.add(numberField);
        
        orderPanel.add(new JLabel("주문 수량:"));
        quantityField = new JTextField();
        orderPanel.add(quantityField);

        orderPanel.add(new JLabel("고객 ID:"));
        clientIdField = new JTextField();
        orderPanel.add(clientIdField);

        centerInputPanel.add(orderPanel, BorderLayout.SOUTH);

        // southpanel - 등록 및 취소 버튼
        registerButton = new JButton("주문 등록");
        cancelButton = new JButton("취소");
        //리스너등록
        registerButton.addActionListener(this);
        cancelButton.addActionListener(this);
        searchButton.addActionListener(this);
        southButtonPanel.add(registerButton);
        southButtonPanel.add(cancelButton);

        // Frame에 패널 추가
        this.add(northSearchPanel, BorderLayout.NORTH);
        this.add(centerInputPanel, BorderLayout.CENTER);
        this.add(southButtonPanel, BorderLayout.SOUTH);
        this.addWindowListener(this);
        this.setVisible(true);
        loadJdb();
        
       
		
	}
	
	private void init() {
		System.out.println("디벅3");
		
		
		
		if(cltdbdao == null) {
			cltdbdao = Client_DAO.getInstance();
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == registerButton) { // 주문 버튼 클릭시
			Order_DTO odto = new Order_DTO();
			
			String cid = clientIdField.getText();
			System.out.println("cid:" + cid);
			int pdnum = Integer.parseInt(numberField.getText());
			Client_DTO cdto= new Client_DTO();
			
			System.out.println("cdto:"+ cdto);
			
			odto.setClient_id(cid);
			//odto.setAdm_id(idTemp);
			//여기서 등록된 고객인지 확인
			//Client_DBdao cltdbdao = null;
			cdto = cltdbdao.selectOne(cid);
			if(cdto == null) {
				System.out.println("등록된 고객이 없습니다");
				JOptionPane.showMessageDialog(this, "등록된 회원이 아님", "확인", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			System.out.println("clientdto debug!!!! : "+cdto);
			//Client_DTO cdto = cltdbdao.selectOne(cid);
			//System.out.println("clientdto debug1" + cltdbdao.selectOne(cid));
			
			String cidTemp= cdto.getID();
			System.out.println("cidTemp" + cidTemp);
			if(cidTemp.equals(cid)) {
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
				
				//이자리 menu2 jtable 리로드
				psfmm2.loadJdb();
				
			}else {
				System.out.println("등록된 아이디가 아님");
				//JOptionPane.showMessageDialog(this, "등록된 회원이 아님", "확인", JOptionPane.INFORMATION_MESSAGE);
				//psfmm2.loadJdb();
			}
			
			
		}else if(e.getSource() == cancelButton) { // 취소버튼클릭시
			this.dispose(); //창닫기
			loadJdb();
			psfmm2.reset();
		}
		if(e.getSource() == searchButton) { // 검색버튼 클릭시
			model.setRowCount(0);
			String temp = searchField.getText();
			ArrayList<Products_DTO> pdlist = pdbdao.searchOne(temp, idTemp);
			
			for(Products_DTO plist : pdlist) {
				String[] data= {
						//plist.getId(),
						String.valueOf(plist.getNum()),
						//plist.getDelivery_Company(),
						plist.getName(),
						plist.getType().toString(),
						String.valueOf(plist.getQuantity()),
						//plist.getInfo(),
						String.valueOf(plist.getPrice()),
						//String.valueOf(plist.getPrice() * plist.getQuantity()), //총가격
						//list.getIndate().toString()
				};
				model.addRow(data);
			}
		}
		
	}
	
	
	
	private void loadJdb() { //jtable에 데이터 추가
		model.setRowCount(0); // 기존행 초기화
		ArrayList<Products_DTO> pdlist = pdbdao.listAll(idTemp);
		
		for(Products_DTO plist : pdlist) {
			String[] data= {
					//plist.getId(),
					String.valueOf(plist.getNum()),
					//plist.getDelivery_Company(),
					plist.getName(),
					plist.getType().toString(),
					String.valueOf(plist.getQuantity()),
					//plist.getInfo(),
					String.valueOf(plist.getPrice()),
					//String.valueOf(plist.getPrice() * plist.getQuantity()), //총가격
					//list.getIndate().toString()
			};
			model.addRow(data);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
				row = jtable.getSelectedRow();
				col = jtable.getSelectedColumn();
				
		        Object value = jtable.getValueAt(row, col);

		        
		        System.out.println("클릭된 행: " + row + ", 열: " + col + ", 값: "
		        + value);
		        
		        numberField.setText(jtable.getValueAt(row, 0).toString());
		        
		      
		        //테스트
		        System.out.println("로우 + 컬럼밸류 : " + jtable.getValueAt(row, 0) +
		        		"로우 + 컬럼밸류tostring : "+jtable.getValueAt(row, 0).toString());
		
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) { // 창닫을시
		// TODO Auto-generated method stub
		//loadJdb();
		psfmm2.reset();
		psfmm2.loadJdb();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
