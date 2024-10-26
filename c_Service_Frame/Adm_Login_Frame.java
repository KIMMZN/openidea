package c_Service_Frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import c_Adm_DAO.Adm_DAO;
import c_Adm_DAO.Adm_DBdao;
import c_Adm_DTO.Adm_DTO;
import c_Products_DAO.Products_DAO;
import c_Products_DAO.Products_DBdao;
import c_Service.Product_Service;

public class Adm_Login_Frame extends JFrame implements ActionListener, ItemListener {
	//
	Adm_DBdao admdbdao = null;
	boolean loginflag = true;
	Products_DBdao pdbdao = null;
	Product_Service pdservice = null;
	
	
	
	
	private JPanel title_p = new JPanel();
	//기본 레이아웃이 flow 레이아웃.. 가운데부터 하나씩 정렬
	private JLabel title = new JLabel("로그인");
	private JPanel center_p = new JPanel();
	private JPanel center_1 = new JPanel();
	private JPanel center_2 = new JPanel();
	
	//c1c 로그인
	JLabel c1 = new JLabel("로그인");
	JPanel c1c = new JPanel();
	JPanel c1c2 = new JPanel();
	JPanel c1c3 = new JPanel();
	
	JButton c1btn = new JButton("로그인");
	JButton c2btn = new JButton("회원가입");
	JLabel c2 = new JLabel("아이디");
	JLabel c3 = new JLabel("비밀번호");
	JTextField j1 = new JTextField();
	JTextField j2 = new JTextField();
	JCheckBox checkBox1 = new JCheckBox("Option 1");
	//JButton c1btn2 = new JButton("체크확인");
	
	
	//JLabel 
	
	public Adm_Login_Frame(){
		super("컴퓨터 재고관리 프로그램 v.1.0");
		init();
		//admLogin();
		//
		//레이아웃 설정
		//setLayout(null);
		this.setBounds(0,0,1080,900);
		center_p.setLayout(null);
		
		
		title_p.add(title);
		center_p.setBackground(Color.cyan);
		this.add(title_p, "North");
		this.add(center_p, "Center");
		
		//center_p 설정
		center_1.setBackground(Color.GRAY);
		//center_1.setPreferredSize(new Dimension(10,10));
		center_1.setBounds(45,50,400,300);
		//center_2.setBackground(Color.blue);
		//
		
		center_p.add(center_1);
		//center_p.add(center_2);
		
		//center_1 설정
		c1c.setLayout(new GridLayout(1, 3));
		c1c.add(c2);
		c1c.add(j1);
		c1c.add(checkBox1);
		c1c2.setLayout(new GridLayout(1,3));
		c1c2.add(c3);
		c1c2.add(j2);
		c1c2.add(c1btn);
		c1c3.setLayout(new GridLayout(1,1));
		c1c3.add(c2btn);
		
		//j1.setPreferredSize(new Dimension(200, 30)); 
		//j2.setPreferredSize(new Dimension(200, 30));
		
		/*c1c.setLayout(new GridLayout(2, 3));
		c1c.add(c2);
		c1c.add(j1);
		c1c.add(checkBox1, "Easdt");
		c1c.add(c3);
		c1c.add(j2);
		c1c.add(c1btn, "East");*/
		
		center_1.setLayout(null);
		//center_1.add(c1, "North");
		//center_1.add(c1btn,"East");
		c1c.setBounds(40,40,320,30);
		center_1.add(c1c);
		center_1.add(c1c2, "Center");
		center_1.add(c1c3, "South");
		c1btn.addActionListener(this);
		
		add(center_p);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
	}
	
	public void init() {
		if(admdbdao == null) {
			admdbdao = new Adm_DAO();
		}
		if(pdbdao == null) {
			pdbdao = new Products_DAO();
		}
	}
	
	public void admLogin() {
		
		System.out.println("로그인");
		Scanner in = new Scanner(System.in);
		System.out.println("ID를 입력하시오");
		String ID = in.nextLine();
		System.out.println("비밀번호를 입력하시오");
		String pass = in.nextLine();
		Adm_DTO admdto = new Adm_DTO();
		admdto.setID(ID);
		admdto.setPassWord(pass);
		if(admdbdao.admLogin(admdto)) {
			System.out.println("로그인성공");
			
			
		}else {
			System.out.println("로그인실패");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
