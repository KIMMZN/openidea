package c_Products_Service_Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import c_Products_DAO.Products_DBdao;
import c_Products_DTO.Products_DTO;

public class Products_Service_Frame_Main_Edit extends JFrame implements ActionListener,
KeyListener		{
	private Products_DBdao pdbdao = null;
	//private JFrame mainFrame;
	Products_Service_Frame_Main mainc = null;
    // 화면 크기 가져오기
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize(); // 화면 크기
    
    //인풋패널및라벨 텍스트필드
    private JPanel inputPanel;
    private JLabel inputNumLabel; private JTextField inputNumField;
    private JLabel inputSupplyLabel; private JTextField inputSupplyField;
    
   
    private JComboBox<Products_DTO.ProductType> typeComboBox; // 콤보박스 타입 Products_DTO.ProductType
    private JLabel inputTypeLabel; //private JTextField inputTypeField;
    private JLabel inputPnameLabel; private JTextField inputPnameField;
    
    private JLabel inputInfoLabel; private JTextField inputInfoField;
    private JLabel inputQuantityLabel; private JTextField inputQuantityField;
    
    private JLabel inputPriceOneLabel; private JTextField inputPriceOneField;
    private JLabel inputTotalPriceLabel; private JTextField inputTotalPriceField;
    
    
    //버튼패널및버튼
    private JButton registerButton, cancelButton;  // 등록 및 취소 버튼
    private JPanel buttonPanel;
    
    //북쪽패널및라벨
    private JPanel titlePanel; private JLabel titleLabel;
    
	Products_Service_Frame_Main_Edit(Products_DBdao dbdao, Products_Service_Frame_Main mainc) {
		pdbdao = dbdao;
    	this.mainc = mainc;
    	
    	
    	this.setTitle("조립 컴퓨터 재고관리 프로그램 v.1.0");
        //this.setSize(screenSize.width, screenSize.height);
    	this.setSize(400,600);
        this.setLocationRelativeTo(null);
        //this.setLayout(new BorderLayout());
        
        //private JPanel inputPanel;
        //rivate JLabel inputNumLabel; private JTextField inputNumField;
        
        //레아이웃 설정
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,2));
        
        //라벨과 입력필드 추가
        inputNumLabel = new JLabel("넘버");
        //inputNumLabel.setHorizontalAlignment(JLabel.CENTER); // 수평 중앙 정렬
        //inputNumLabel.setVerticalAlignment(JLabel.CENTER); 
        inputNumField = new JTextField();
        inputNumField.setText(mainc.getWestTextField0().getText());
        inputNumField.setEditable(false);
        inputPanel.add(inputNumLabel);
        inputPanel.add(inputNumField);
        
        inputSupplyLabel = new JLabel("공급사");
        inputSupplyField = new JTextField();
        inputSupplyField.setText(mainc.getWestTextField1().getText());
        inputPanel.add(inputSupplyLabel);
        inputPanel.add(inputSupplyField);
        
        inputTypeLabel = new JLabel("타입"); 
        typeComboBox = new JComboBox<>(Products_DTO.ProductType.values());
        inputPanel.add(inputTypeLabel);
        inputPanel.add(typeComboBox);
        
        inputPnameLabel = new JLabel("상품명");
        inputPnameField = new JTextField();
        inputPnameField.setText(mainc.getWestTextField3().getText());
        inputPanel.add(inputPnameLabel);
        inputPanel.add(inputPnameField);
        
        inputInfoLabel = new JLabel("정보");
        inputInfoField = new JTextField();
        inputInfoField.setText(mainc.getWestTextField4().getText());
        inputPanel.add(inputInfoLabel);
        inputPanel.add(inputInfoField);
        
        inputQuantityLabel = new JLabel("수량");
        inputQuantityField = new JTextField();
        inputQuantityField.setText(mainc.getWestTextField5().getText());
        inputPanel.add(inputQuantityLabel);
        inputPanel.add(inputQuantityField);
        
        inputPriceOneLabel = new JLabel("가격(개당)");
        inputPriceOneField = new JTextField();
        inputPriceOneField.setText(mainc.getWestTextField6().getText());
        //inputPriceOneField.setEditable(false);
        inputPanel.add(inputPriceOneLabel);
        inputPanel.add(inputPriceOneField);
        
        inputTotalPriceLabel = new JLabel("총가격");
        inputTotalPriceField = new JTextField();
        inputTotalPriceField.setText(mainc.getWestTextField7().getText());
        inputPanel.add(inputTotalPriceLabel);
        inputPanel.add(inputTotalPriceField);
        
        
        //타이틀 패널및 라벨
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titleLabel = new JLabel("수정");
        titlePanel.add(titleLabel);
        
        
        buttonPanel = new JPanel();
        registerButton = new JButton("수정");
        cancelButton = new JButton("취소");
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        //mainc.getWestTextField0();
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(inputPanel, BorderLayout.CENTER);
        
        
        //창닫기시 리셋
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainc.reset(); 
            }
        });
        
        
        //리스너
        cancelButton.addActionListener(this);
        registerButton.addActionListener(this);
        
        inputQuantityField.addKeyListener(this);
        inputTotalPriceField.addKeyListener(this);
        inputPriceOneField.addKeyListener(this);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//수정버튼
		if(e.getSource() == registerButton) {
			int numTemp = Integer.parseInt(inputNumField.getText());
			Products_DTO pdto = pdbdao.selectOne(numTemp);
			String supplyTemp = inputSupplyField.getText();
			Products_DTO.ProductType type = (Products_DTO.ProductType)typeComboBox.getSelectedItem();
			//String typeBoxTemp = typeComboBox.getName();
			//System.out.println("타입박스테스트 "+ typeBoxTemp);
			String nameTemp = inputPnameField.getText();
			String infoTemp = inputInfoField.getText();
			int quantityTemp = Integer.parseInt(inputQuantityField.getText());
			int PriceOneTemp = Integer.parseInt(inputPriceOneField.getText());
			pdto.setDelivery_Company(supplyTemp);
			pdto.setType(type);
			pdto.setName(nameTemp);
			pdto.setInfo(infoTemp);
			pdto.setQuantity(quantityTemp);
			pdto.setPrice(PriceOneTemp);
			pdbdao.update(pdto);
			 JOptionPane.showMessageDialog(this, "수정 되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("수정완료");
		}else if (e.getSource() == cancelButton) {
            // 취소 버튼 클릭 시 창 닫기
            this.dispose();
            //mainFrame.setEnabled(true); //부모 프레임 다시 활성화함
            //mainFrame.reset();
            mainc.reset();
        }
		
	}


	private void calculTotalPrice() { // 종합가격계산
	    try {
	        int priceOne = Integer.parseInt(inputPriceOneField.getText());
	        int totalQuantity = Integer.parseInt(inputQuantityField.getText());

	        if (priceOne > 0 && totalQuantity > 0) {
	            int totalPrice = priceOne * totalQuantity;
	            inputTotalPriceField.setText(Integer.toString(totalPrice));
	        } else {
	            inputTotalPriceField.setText("");
	        }
	    } catch (NumberFormatException e) {
	        
	        inputTotalPriceField.setText("");
	    }
	}

	private void calculPriceOne() { //개당가격 계산
	    try {
	        int totalPrice = Integer.parseInt(inputTotalPriceField.getText());
	        int totalQuantity = Integer.parseInt(inputQuantityField.getText());

	        if (totalQuantity > 0 && totalPrice > 0) {
	            int priceOne = totalPrice / totalQuantity;
	            inputPriceOneField.setText(Integer.toString(priceOne));
	        } else {
	            inputPriceOneField.setText("");
	        }
	    } catch (NumberFormatException e) {
	        
	        inputPriceOneField.setText("");
	    }
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
		 if (e.getSource() == inputQuantityField || e.getSource() == inputPriceOneField) {
			 calculTotalPrice();  // 수량이나 개별 가격이 변경되었을 때 총 가격을 계산
		    } else if (e.getSource() == inputTotalPriceField) {
		    	calculPriceOne();  // 총 가격이 변경되었을 때 개별 가격을 계산
		    }
	}
	

}
