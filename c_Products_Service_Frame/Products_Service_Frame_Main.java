package c_Products_Service_Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import c_Products_DAO.Products_DAO;
import c_Products_DAO.Products_DBdao;
import c_Products_DTO.Products_DTO;
import c_Service_Frame.Adm_Login_Frame2;

public class Products_Service_Frame_Main extends JFrame implements ActionListener,
ItemListener, MouseListener {
	private Products_DBdao pdbdao = null;
	private Products_Service_Frame_Main_Add psfma = null;
	private Products_Service_Frame_Main_Edit psfmae = null;
	private Products_Service_Frame_Main_menu2 psfm2 = null;
	
	//
	private Adm_Login_Frame2 admlf2 = null;
	
	private JPanel northPanel; private JLabel northLabel;
	private JPanel northSouthPanel; private JLabel northSouthLabel;
	//northSouth_c
	private JPanel northSouthPanel_c; private JLabel northSouthLabel_c;
	private JTextField northSouthField_c; private JButton northSouthbutton_c;
	private JButton northSouthbutton_c1;
	
	//오른쪽 패널
	private JPanel eastPanel; private JLabel eastJlabel;
	private JButton buttona1;private JButton buttona2;private JButton buttona3;
	
	//왼쪽 패널
	private JPanel westPanel; private JLabel westJlabel6;private JLabel westJlabel2;private JLabel westJlabel3;
	private JLabel westJlabel4;private JLabel westJlabel5;private JLabel westJlabel7; private JLabel westJlabel1;
	private JLabel westJlabel0;
	private JLabel westLabelid; private JTextField westTextFieldid;
	private JTextField westTextField1;
	
	
	

	private JTextField westTextField2;private JTextField westTextField3;
	private JTextField westTextField4;private JTextField westTextField5;private JTextField westTextField6;
	private JTextField westTextField7;private JTextField westTextField0;
	//현재화면크기 가져오기
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension screenSize = toolkit.getScreenSize(); // 화면크기
	
	//jtable
	private JTable jtable;
	private String colNames[] = {"등록ID", "넘버","공급사","타입","상품명","정보","수량","개당가격","총가격","등록일"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JScrollPane scrollPane;
	
	//삭제하기 위한 jtable row,col을 멤버변수로
	private int row; // jtable row
	private int col; // jtable cul
	
	//id를 활용하기 위한 변수
	String idTemp = null; 
	
	//위 옵션버튼
	JButton testjbutton1;
	JButton testjbutton2;
	
	
		
	public Products_Service_Frame_Main (Adm_Login_Frame2 alf2, String idTemp) {
		admlf2 = alf2;
		
		
		
		this.setTitle("조립 컴퓨터 재고관리 프로그램 v.1.0");
		this.setLayout(new BorderLayout());
		this.setSize(screenSize.width, screenSize.height);
		//northPanel 고객 환영합니다
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.setBackground(Color.LIGHT_GRAY);
		northPanel.setBorder(new LineBorder(Color.black,2));
		//패널 높이 조정
		northPanel.setPreferredSize(new Dimension(this.getWidth(), 100));
		
		//라벨
		
		//아이디 문구 환영문구		
		
		//String idtemp = idfield.getText().toString();
		this.idTemp = idTemp;
		System.out.println("id확인" + idTemp);
		
		//String idtemp11 = 
		 //String idfiedl = admlf2.getLoginField().getText();
		
		
		if(idTemp == null) {
			System.out.println("idtemp11는 비었음");
		}else {
			System.out.println(idTemp+"님환영합니다");
			
		}
		
		System.out.println("뭔데안돼");
		String welcome = " 님 환영합니다";
		String idtemp1 = idTemp + welcome;
		northLabel = new JLabel(idtemp1);
		
		init();
		
		//norh southern panel
		northSouthPanel = new JPanel();
		northSouthPanel.setLayout(new BorderLayout());
		northSouthPanel.setBackground(Color.white);
		northSouthPanel.setBorder(new LineBorder(Color.black, 2));
		northSouthPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
		
		//East panel
		//private JPanel eastPanel; private JLabel eastJlabel;
		eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(3, 1));
		eastPanel.setBackground(Color.white);
		eastPanel.setBorder(new LineBorder(Color.black, 2));
		eastPanel.setPreferredSize(new Dimension(100, this.getHeight()));
		//private JButton buttona1;private JButton buttona2;private JButton buttona3;
		buttona1 = new JButton("등록");buttona2 = new JButton("수정");buttona3 = new JButton("삭제");
		eastPanel.add(buttona1); eastPanel.add(buttona2); eastPanel.add(buttona3);
		
		//west panel
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(18, 1));
		westPanel.setBackground(Color.white);
		westPanel.setBorder(new LineBorder(Color.black, 2));
		westPanel.setPreferredSize(new Dimension(200, this.getHeight()));
		
		
		//westpanel _ 라벨과 텍스트필드
		
		westLabelid = new JLabel ("등록id");
		westLabelid.setOpaque(true);
		westTextFieldid = new JTextField();
		westLabelid.setBackground(Color.GRAY); 
		
		
		westJlabel0 = new JLabel ("넘버");
		westJlabel0.setOpaque(true);
		westTextField0 = new JTextField();
		westJlabel0.setBackground(Color.GRAY); 
		
		JLabel westJlabel1 = new JLabel ("공급사");
		westJlabel1.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel1.setBackground(Color.GRAY);  // 배경색 설정
		westTextField1 = new JTextField ();
		JLabel westJlabel2 = new JLabel ("타입");
		westJlabel2.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel2.setBackground(Color.GRAY);  // 배경색 설정
		westTextField2 = new JTextField ();
		JLabel westJlabel3 = new JLabel ("상품명");
		westTextField3 = new JTextField ();
		westJlabel3.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel3.setBackground(Color.GRAY);  // 배경색 설정
		JLabel westJlabel4 = new JLabel ("정보");
		westTextField4 = new JTextField ();
		westJlabel4.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel4.setBackground(Color.GRAY);  // 배경색 설정
		JLabel westJlabel5 = new JLabel ("수량");
		westTextField5 = new JTextField ();
		westJlabel5.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel5.setBackground(Color.GRAY);  // 배경색 설정
		JLabel westJlabel6 = new JLabel ("개당가격");
		westTextField6 = new JTextField ();
		westJlabel6.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel6.setBackground(Color.GRAY);  // 배경색 설정
		JLabel westJlabel7 = new JLabel ("총가격");
		westTextField7 = new JTextField ();
		westJlabel7.setOpaque(true);  // JLabel을 불투명하게 설정
		westJlabel7.setBackground(Color.GRAY);  // 배경색 설정
		
		
		westPanel.add(westLabelid);
		westPanel.add(westTextFieldid);
		westPanel.add(westJlabel0);
		westPanel.add(westTextField0);
		westPanel.add(westJlabel1);
		westPanel.add(westTextField1);
		westPanel.add(westJlabel2);
		westPanel.add(westTextField2);
		westPanel.add(westJlabel3);
		westPanel.add(westTextField3);
		westPanel.add(westJlabel4);
		westPanel.add(westTextField4);
		westPanel.add(westJlabel5);
		westPanel.add(westTextField5);
		westPanel.add(westJlabel6);
		westPanel.add(westTextField6);
		westPanel.add(westJlabel7);
		westPanel.add(westTextField7);
		
		//westPanel
		
		//eastJlabel = new JLabel("동쪽임시");
		//eastPanel.add(eastJlabel);
		
		
		//기본 선택 버튼
		//private JPanel northSouthPanel_c; private JLabel northSouthLabel_c;
		//private JTextField northSouthField_c; private JButton northSouthbutton_c;
		northSouthLabel_c = new JLabel("검색");
		northSouthLabel_c.setHorizontalAlignment(JLabel.CENTER); // 수평 중앙 정렬
		northSouthLabel_c.setVerticalAlignment(JLabel.CENTER); 
		
		northSouthField_c = new JTextField();
		northSouthbutton_c = new JButton("검색"); //검색버튼
		northSouthbutton_c1 = new JButton("새로고침"); //새로고침버튼
		northSouthPanel_c = new JPanel();
		northSouthPanel_c.setLayout(new GridLayout(1, 4));
		northSouthPanel_c.setBorder(new LineBorder(Color.black, 2));
		northSouthPanel_c.add(northSouthLabel_c);
		northSouthPanel_c.add(northSouthField_c);
		northSouthPanel_c.add(northSouthbutton_c);
		northSouthPanel_c.add(northSouthbutton_c1);
		
		northSouthPanel.setBorder(BorderFactory.createEmptyBorder(0,200,0,200));
		
		northSouthPanel.add(northSouthPanel_c, "Center");
		
		
		
			
		//Jtable		
		jtable = new JTable(model);
		scrollPane = new JScrollPane(jtable);
		scrollPane.setPreferredSize(new Dimension(800,400));
		
		//액션리스너
		buttona1.addActionListener(this);
		
		//add north쪽 메뉴선택창
		JPanel northPanel_C = new JPanel(new GridLayout(1, 3));
		//JPanel testpanel0 = new JPanel();
		//testpanel0.setBackground(Color.lightGray);
		JLabel testjlabel0 = new JLabel("메뉴");
		testjlabel0.setHorizontalAlignment(SwingConstants.CENTER); // 수평 중앙 정렬
	    testjlabel0.setVerticalAlignment(SwingConstants.CENTER);   // 수직 중앙 정렬
		//JPanel testpanel1 = new JPanel();
		//JLabel testjlabel1 = new JLabel("테스트라벨1");
		testjbutton1 = new JButton("재품등록");
		testjbutton2 = new JButton("고객주문");
		
		//JPanel testpanel2 = new JPanel();
		//JLabel testjlabel2 = new JLabel("테스트라벨2");
		
		
		//testpanel0.add(testjlabel0);
		//testpanel1.add(testjlabel1);
		//testpanel2.add(testjlabel2);
		//testpanel1.add(testjbutton1);
		//testpanel2.add(testjbutton2);
		northPanel_C.add(testjlabel0);
		northPanel_C.add(testjbutton1);
		northPanel_C.add(testjbutton2);
		
		
		northPanel.add(northPanel_C, "Center");
		northPanel.add(northSouthPanel, "South");
		northPanel.add(northLabel, "East");
		this.add(westPanel, "West");
		this.add(eastPanel, "East");
		this.add(scrollPane, "Center");
		this.add(northPanel, "North");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		loadJdb();
		//리스너 등록;
		northSouthbutton_c.addActionListener(this);
		northSouthField_c.addActionListener(this);
		northSouthbutton_c1.addActionListener(this);
		buttona2.addActionListener(this);
		jtable.addMouseListener(this);
		buttona3.addActionListener(this);
		//
		testjbutton1.addActionListener(this);
		testjbutton2.addActionListener(this);
		
	}
	
	private void init() {
		if(pdbdao == null) {
			pdbdao = new Products_DAO();
		}

	}
	

	
	private void loadJdb() { //jtable에 데이터 추가
		model.setRowCount(0); // 기존행 초기화
		
		
		ArrayList<Products_DTO> pdlist = pdbdao.listAll(idTemp);
		
		for(Products_DTO plist : pdlist) {
			String[] data= {
					plist.getId(),
					String.valueOf(plist.getNum()),
					plist.getDelivery_Company(),
					plist.getType().toString(),
					plist.getName(),
					plist.getInfo(),
					String.valueOf(plist.getQuantity()),
					String.valueOf(plist.getPrice()),
					String.valueOf(plist.getPrice() * plist.getQuantity()), //총가격
					plist.getIndate().toString()
			};
			model.addRow(data);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == buttona1) {//등록
			add();
		}
		
		if(e.getSource() == buttona2) {//수정
			edit();
			
		}
		if(e.getSource() == buttona3) {//삭제
			//delete();
			//loadJdb();
			//JOptionPane.showMessageDialog(this, "삭제하시겠습니까?", "삭제 확인창", JOptionPane.WARNING_MESSAGE );
			//JOptionPane.showMessageDialog(this,"ID나 비밀번호를 확인하세요", "로그인 실패", JOptionPane.ERROR_MESSAGE );
			int result = JOptionPane.showConfirmDialog(this, "선택한 데이터를 삭제하시겠습니까?", "경고창", 
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION) {
				System.out.println("예스 테스트");
				delete();
				loadJdb();
			}else if(result == JOptionPane.NO_OPTION) {
				System.out.println("노 테스트");
			}
			
				
		}
		
		if(e.getSource() == northSouthbutton_c) { // 검색버튼
			model.setRowCount(0); // 기존행 초기화
			String searhTemp= northSouthField_c.getText();
			//String searhTemp1= northSouthField_c.getText();
			ArrayList<Products_DTO> pdlist = pdbdao.searchOne(searhTemp, idTemp);
			
			for(Products_DTO plist : pdlist) {
				String[] data= {
						plist.getId(),
						String.valueOf(plist.getNum()),
						plist.getDelivery_Company(),
						plist.getType().toString(),
						plist.getName(),
						plist.getInfo(),
						String.valueOf(plist.getQuantity()),
						String.valueOf(plist.getPrice()),
						String.valueOf(plist.getPrice() * plist.getQuantity()), //총가격
						plist.getIndate().toString()
				};
				model.addRow(data);
			}
		}
		
		if(e.getSource() == northSouthField_c) { //검색필드에서 엔터키 입력시
			northSouthbutton_c.doClick();
		}
		
		if(e.getSource() == northSouthbutton_c1) { //새로고침 버튼
			loadJdb();
		}
		
		if(e.getSource() == testjbutton1) { // 현재창
			this.setVisible(true);
		}
		if(e.getSource() == testjbutton2) { // 주문창
			openClientOrder();
		}
		
	}
	
	private void add() {
		//Products_Service_Frame_Main_Add psfma = null;
		if (psfma == null) {
			psfma = new Products_Service_Frame_Main_Add(pdbdao, this, idTemp);
		}
		
	}
	
	private void edit() {
		Products_Service_Frame_Main_Edit psfmae = null;
		if(psfmae == null) {
			psfmae = new Products_Service_Frame_Main_Edit(pdbdao, this);
		}
		
	}
	
	
	public void reset() { //리셋
		psfma = null;
		psfmae = null;
		psfm2 = null;
		//psfmain = null;
		loadJdb();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		row = jtable.getSelectedRow();
		col = jtable.getSelectedColumn();
		
        Object value = jtable.getValueAt(row, col);

        
        System.out.println("클릭된 셀 - 행: " + row + ", 열: " + col + ", 값: "
        + value);
        westTextFieldid.setText(jtable.getValueAt(row, 0).toString());
        westTextField0.setText(jtable.getValueAt(row, 1).toString());
        westTextField1.setText(jtable.getValueAt(row, 2).toString());
        westTextField2.setText(jtable.getValueAt(row, 3).toString());
        westTextField3.setText(jtable.getValueAt(row, 4).toString());
        westTextField4.setText(jtable.getValueAt(row, 5).toString());
        westTextField5.setText(jtable.getValueAt(row, 6).toString());
        westTextField6.setText(jtable.getValueAt(row, 7).toString());
        westTextField7.setText(jtable.getValueAt(row, 8).toString());
        
       
        //테스트
        System.out.println("로우 + 컬럼밸류 : " + jtable.getValueAt(row, 0) +
        		"로우 + 컬럼밸류tostring : "+jtable.getValueAt(row, 0).toString());
		
	}
	private void delete() {
		System.out.println("삭제할 제품의 넘버를 입력하시오");
		//Object valued = jtable.getValueAt(row, col);
		
		row = jtable.getSelectedRow();
		col = jtable.getSelectedColumn();
		
        //Object value = jtable.getValueAt(row, col);
        
        int numTemp = Integer.parseInt(jtable.getValueAt(row, 1).toString());
        pdbdao.delete(numTemp);
        	
        JOptionPane.showMessageDialog(this, "삭제 되었습니다", "확인", JOptionPane.INFORMATION_MESSAGE);
        reset();
	}
	
	private void openClientOrder() {
		if (psfm2 == null) {
			psfm2 = new Products_Service_Frame_Main_menu2(this ,idTemp, pdbdao);
			psfm2.setVisible(true);
			this.setVisible(false);
		}
		// Products_DBdao pdbdao
		
		
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
	
	//
	public JTextField getWestTextField1() {
		return westTextField1;
	}

	public void setWestTextField1(JTextField westTextField1) {
		this.westTextField1 = westTextField1;
	}

	public JTextField getWestTextField2() {
		return westTextField2;
	}

	public void setWestTextField2(JTextField westTextField2) {
		this.westTextField2 = westTextField2;
	}

	public JTextField getWestTextField3() {
		return westTextField3;
	}

	public void setWestTextField3(JTextField westTextField3) {
		this.westTextField3 = westTextField3;
	}

	public JTextField getWestTextField4() {
		return westTextField4;
	}

	public void setWestTextField4(JTextField westTextField4) {
		this.westTextField4 = westTextField4;
	}

	public JTextField getWestTextField5() {
		return westTextField5;
	}

	public void setWestTextField5(JTextField westTextField5) {
		this.westTextField5 = westTextField5;
	}

	public JTextField getWestTextField6() {
		return westTextField6;
	}

	public void setWestTextField6(JTextField westTextField6) {
		this.westTextField6 = westTextField6;
	}

	public JTextField getWestTextField7() {
		return westTextField7;
	}

	public void setWestTextField7(JTextField westTextField7) {
		this.westTextField7 = westTextField7;
	}

	public JTextField getWestTextField0() {
		return westTextField0;
	}

	public void setWestTextField0(JTextField westTextField0) {
		this.westTextField0 = westTextField0;
	}
	
	
	

}
