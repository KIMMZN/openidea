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

import c_Client_DAO.Client_DBdao;
import c_Client_DTO.Client_DTO;
import c_Client_Service_Frame_Menu.c_Client_Service_Frame_Menu;

public class c_Client_Service_Frame_Join extends JFrame implements ActionListener, ItemListener {
	    private Client_DBdao cdbdao = null;
	    private c_Client_Service_Frame_Main csfm = null;
		private JButton loginButton, registerButton;
	 
	    private JTextField userIdField;
	    private JPasswordField passwordField;
	    private JTextField userNameField;
	    private JTextField userPnumField;
	    private JTextField userAddressField;
	    private JPasswordField passwordFieldr; //비밀번호 확인필드
	    
	
	
	c_Client_Service_Frame_Join(Client_DBdao cdbdao, c_Client_Service_Frame_Main csfm) {
		this.cdbdao = cdbdao;
		this.csfm = csfm;
		csfm = null;
		  // JFrame 기본 설정
        setTitle("고객 관리 프로그램 v.1.0");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 패널 설정
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        // 10, 10
        // 사용자 이름 레이블 및 텍스트 필드
        panel.add(new JLabel("Id:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        // 비밀번호 레이블 및 텍스트 필드
        panel.add(new JLabel("패스워드:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
      // 비밀번호 재확인 레이블 및 필드;
        panel.add(new JLabel("패스워드:"));
        passwordFieldr = new JPasswordField();
        panel.add(passwordFieldr);
        
        
        //이름
        panel.add(new JLabel("이름:"));
        userNameField = new JTextField();
        panel.add(userNameField);
        //폰넘버
        panel.add(new JLabel("핸드폰번호:"));
        userPnumField = new JTextField();
        panel.add(userPnumField);
        //주소
        panel.add(new JLabel("주소:"));
        userAddressField = new JTextField();
        panel.add(userAddressField);

        // 로그인 버튼
        loginButton = new JButton("회원가입");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        // 취소버튼
        registerButton = new JButton("취소");
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // JFrame에 패널 추가
        add(panel);

        // 화면에 표시
        setVisible(true);
		
		
		
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(e.getSource() == loginButton) {
			//String id = userIdField.getText();
            String password = new String(passwordField.getPassword());
            String passwordr = new String(passwordFieldr.getPassword());
            System.out.println("디버그1");
            if(password.equals(passwordr)) {
            	flag = true;
            	Client_DTO cdto = new Client_DTO();
            	String idtemp = userIdField.getText();
            	String passwordFieldtemp = password;
            	String nameTemp = userNameField.getText();
				String pnumTemp = userPnumField.getText();
				String addressTemp = userAddressField.getText();
				cdto.setID(idtemp);
				cdto.setPassWord(passwordFieldtemp);
				System.out.println("디버그2");
				cdto.setName(nameTemp);
				cdto.setPhoneNumber(pnumTemp);
				cdto.setAddress(addressTemp);
				cdbdao.add(cdto);
				JOptionPane.showMessageDialog(this, "회원가입에 성공했습니다 ","회원가입 ㄴ성공",JOptionPane.INFORMATION_MESSAGE);
            	
            }else if(flag == false) {
    			JOptionPane.showMessageDialog(this, "비밀번호가 다릅니다", "비밀번호 재확인 실패", JOptionPane.ERROR_MESSAGE );
    		}
		}
		if(e.getSource() == registerButton) {
			//JOptionPane.showMessageDialog(this, "비밀번호가 다릅니다", "비밀번호 재확인 실패", JOptionPane.ERROR_MESSAGE );
			csfm.reset();
			this.dispose();
		}
		
	}

}
