package c_Service_Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import c_Adm_DAO.Adm_DAO;
import c_Adm_DAO.Adm_DBdao;
import c_Adm_DTO.Adm_DTO;
import c_Products_DTO.Products_DTO;
import c_Products_Service_Frame.Products_Service_Frame_Main;
import c_Service_Frame_Join.Adm_Join;

public class Adm_Login_Frame2 extends JFrame implements ActionListener, ItemListener {
	//
	Adm_DBdao admdbdao = null;

	Adm_Join Admjoin = null;
	Products_Service_Frame_Main psfm = null;
	//Products_Service_Frame_Main psfm1 = null;
	
	public Products_Service_Frame_Main getPsfm() {
		return psfm;
	}

	private JPanel centerP;
	private JPanel cneterP_1;
	
	BufferedImage img = null; //이미지
	
	 JLabel Login; //로그인안 
	//= new JLabel("로그인");
	private JLabel passLabel;
	JLabel idLabel;
	private JTextField loginField; // 로그인텍스트필드

	public JTextField getLoginField() {
		return loginField;
	}
	
	//public String idtemp11 = loginField.getText();
	
	
	private JPasswordField passField; // 패스워드필드
	JButton button1;	// 로그인버튼1
	JButton joinButton; // 회원가입버튼
	JButton exitButton; // 종료버튼
	
	
	
	
	
	
	//아이디
	String idTemp;
	
	public Adm_Login_Frame2(){
		//super("컴퓨터 재고관리 프로그램 v.1.0");
		init();
		
		this.setTitle("컴퓨터 재고관리 프로그램 v.1.0");
		this.setSize(1080,800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//레이아웃 설정
		this.setLayout(null);
		JLayeredPane layeredP = new JLayeredPane();
		layeredP.setBounds(0,0,1080,800);
		layeredP.setLayout(null);
		
		
		
		
		try { //이미지 로드
			img = ImageIO.read(new File("img/login.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("이미지 로드 실패");
			System.exit(0);
		}
		
		//MyPanel panel1 = new MyPanel();
		MyPanel panel = new MyPanel();
		panel.setBounds(0,0,1080,800);
		

		
		//center 로그인 작은창
		centerP = new JPanel();
		centerP.setLayout(null);
		centerP.setBackground(Color.WHITE);
		centerP.setBounds(400,200,300,250);
		centerP.setBorder(new LineBorder(Color.black,2));
		
		//로그인 윗 "로그인텍스트"패널
		cneterP_1 = new JPanel();
		cneterP_1.setBackground(new Color(100, 150, 255));
		cneterP_1.setBounds(5,5,290,30);
		
		
		//로그인 윗 "로그인텍스트"패널 - 라벨
		Login = new JLabel("로그인");
		
		
		
		//로그인필드
		idLabel = new JLabel("로그인");
		idLabel.setBounds(20,40,100,50);  
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16)); //
		loginField = new JTextField();
		loginField.setBounds(90,55,80,20);
		
		//비밀번호 필드
		passLabel = new JLabel("비밀번호");
		passLabel.setBounds(20, 80, 100, 50);
		passLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		passField = new JPasswordField ();
		passField.setBounds(90,95,80,20);
		
		//로그인 버튼
		button1 = new JButton("로그인");
		button1.setBounds(195,90,80,25);
		
		//회원가입 버튼
		joinButton = new JButton("회원가입");
		joinButton.setBounds(20,170,100,25);
		
		//끝내기 버튼
		exitButton = new JButton("종료");
		exitButton.setBounds(195,170,80,25);
		//exitButton.setbou
		
		
		
		cneterP_1.add(Login, Integer.valueOf(1));
		
		centerP.add(cneterP_1, Integer.valueOf(1));
		centerP.add(idLabel, Integer.valueOf(1));centerP.add(loginField, Integer.valueOf(1));
		centerP.add(passLabel, Integer.valueOf(1));centerP.add(passField, Integer.valueOf(1));
		centerP.add(button1, Integer.valueOf(1));
		centerP.add(joinButton, Integer.valueOf(1));
		centerP.add(exitButton, Integer.valueOf(1));
		
		layeredP.add(panel, Integer.valueOf(0));
		layeredP.add(centerP, Integer.valueOf(1));
		
		this.add(layeredP);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//리스너등록
		button1.addActionListener(this);
		passField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button1.doClick();
			}
		});
		exitButton.addActionListener(this);
		joinButton.addActionListener(this);
		
		
		
	}
	
	public void init() {
		if(admdbdao == null) {
			admdbdao = new Adm_DAO();
		}
	
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//로그인 버튼 누를시
				if(e.getSource() == button1 ) {
					idTemp = loginField.getText();
					System.out.println("로그인필드id"+idTemp );//
					char[] passTemp = passField.getPassword();
					//char[]를 string으로 변환
					String passwordString = new String(passTemp);
					if(!idTemp.isEmpty() && !passwordString.isEmpty() ) {
						java.util.Arrays.fill(passTemp, '0');
						
						System.out.println(idTemp + "/ " + passwordString );
						//DTO 저장하고, DAO를 통해서 DB에 저장
						Adm_DTO amdto = new Adm_DTO();
						amdto.setID(idTemp);
						amdto.setPassWord(passwordString);
						
						if(admdbdao.admLogin(amdto)) {
							System.out.println("로그인 성공" + idTemp);
							psfm(); //재고관리객체생성 메서드
							//psfm = new Products_Service_Frame_Main(this, idTemp);
							System.out.println("아이디확인" + idTemp);
						}else {
							JOptionPane.showMessageDialog(centerP, "ID나 비밀번호를 확인하세요", "로그인 실패", JOptionPane.ERROR_MESSAGE );
							//System.out.println("실패");
						}
					}else {
						JOptionPane.showMessageDialog(centerP, "ID나 비밀번호를 입력하시오", "로그인 실패", JOptionPane.ERROR_MESSAGE );
					}
				}
				//종료 버튼 누를시
				if(e.getSource() == exitButton) {
					this.dispose();
					System.exit(0);
					
				}
				
				//회원가입 버튼 누를시
				if(e.getSource() == joinButton) {
					amdJoin();
					
				}
				
				
				
		
	}
	
	class MyPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
	
	private void amdJoin() { //회원가입객체생성
		if(Admjoin == null) {
			Admjoin = new Adm_Join(admdbdao);
		}
	}
	
	private void psfm() { // 재고관리객체생성
		if(psfm == null) {
			psfm = new Products_Service_Frame_Main(this, idTemp);
			System.out.println("아이디확인" + idTemp);
			this.setVisible(false);
		}
	}

	
	
}
