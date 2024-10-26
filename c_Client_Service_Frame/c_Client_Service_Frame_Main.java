package c_Client_Service_Frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import c_Client_DAO.Client_DAO;
import c_Client_DAO.Client_DBdao;
import c_Client_DTO.Client_DTO;
import c_Client_Service_Frame_Menu.c_Client_Service_Frame_Menu;

public class c_Client_Service_Frame_Main extends JFrame implements ActionListener, ItemListener {
	c_Client_Service_Frame_Join csfj = null; //회원가입 객체
	Client_DBdao cdbdao = null; //고객 관리 dbdao 객체
	private c_Client_Service_Frame_Menu csfmenu = null;
	
	
    private JButton loginButton, registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    String username = null;

    public c_Client_Service_Frame_Main() {
    	init();
        // JFrame 기본 설정
        setTitle("고객 관리 프로그램 v.1.0");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 패널 설정
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        // 10, 10
        // 사용자 이름 레이블 및 텍스트 필드
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // 비밀번호 레이블 및 텍스트 필드
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // 로그인 버튼
        loginButton = new JButton("로그인");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        // 회원가입 버튼
        registerButton = new JButton("회원가입");
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // JFrame에 패널 추가
        add(panel);

        // 화면에 표시
        setVisible(true);
    }
    
   
    private void init() {
        
        if (cdbdao == null) {
            cdbdao = Client_DAO.getInstance();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // 체크박스나 드롭다운 등 아이템의 상태 변화 처리
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 버튼 클릭 이벤트 처리
        if (e.getSource() == loginButton) {
            // 로그인 처리 로직 추가
            username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if(!username.isEmpty() && !password.isEmpty()) {
            	
            	Client_DTO cdto = new Client_DTO();
            	cdto.setID(username);
            	cdto.setPassWord(password);
            	
            	if(cdbdao.clientLogin(cdto)) {
            		System.out.println("로그인 성공" + username);
            		toMenu();
            		//JOptionPane.showMessageDialog(this, "로그인 성공 ","로그인성공",JOptionPane.INFORMATION_MESSAGE);
            	}else {
            		JOptionPane.showMessageDialog(this, "ID나 비밀번호를 확인하세요", "로그인 실패", JOptionPane.ERROR_MESSAGE );
            	}
            }else {
            	JOptionPane.showMessageDialog(this, "ID나 비밀번호를 확인하세요", "로그인 실패", JOptionPane.ERROR_MESSAGE );
            }
           
        } else if (e.getSource() == registerButton) {
        	join();
        }
    }
    
    private void join() {
    	
    	if(csfj==null) {
    		csfj = new c_Client_Service_Frame_Join(cdbdao,this);
    	}
    }
    
    void reset() {
    	csfj = null; 
    }
    
    private void toMenu() {
    	if(csfmenu == null) {
    		csfmenu = new c_Client_Service_Frame_Menu(username,cdbdao);
    	}
    	
    }
	 
}
