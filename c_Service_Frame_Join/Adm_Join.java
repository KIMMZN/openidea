package c_Service_Frame_Join;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import c_Adm_DAO.Adm_DBdao;
import c_Adm_DTO.Adm_DTO;

public class Adm_Join extends JFrame implements ActionListener {
	Adm_DBdao admdbdao = null;
	
	//JPanel mainP;  
	JLabel idLabel; JTextField idTextField;
	
	JButton joinButton = new JButton();
	JLabel passLabel; JPasswordField passTextField; //JTextField passTextField; 
	JLabel passLabelr; JPasswordField passTextFieldr; //비밀번호 재확인
	JLabel nameLabel; JTextField nameTextField;
	JLabel pnumLabel; JTextField pnumTextField;
	JLabel addressLabel; JTextField addressTextField;
	//JButton joinButton;
	JButton exitButton;
	
	public Adm_Join(Adm_DBdao d) {
		admdbdao = d; //받은 Adm_DBdao 객체의 주소값 입력 
		
		this.setTitle("회원 가입");
		//this.setSize(400,300);
		this.setBounds(500,150,320,800);
		this.setLayout(null);
		
		
		//메인 패널
		//mainP = new JPanel();
		//mainP.setBounds(0,0,400,300);
		//mainP.setLayout(null);
		
		//id
		idLabel = new JLabel("아이디:");
		idLabel.setBounds(50,20,100,50);
		idTextField = new JTextField();
		idTextField.setBounds(50,70,200,30);
		
		//비밀번호
		passLabel = new JLabel("비밀번호:");
		passLabel.setBounds(50,120,100,50);
		passTextField = new JPasswordField();
		passTextField.setBounds(50,160,200,30);
		//비밀번호 재확인
		passLabelr = new JLabel("비밀번호 재확인:");
		passLabelr.setBounds(50,200,100,50);
		passTextFieldr = new JPasswordField();
		passTextFieldr.setBounds(50,240,200,30);
		//이름
		nameLabel = new JLabel("이름:");
		nameLabel.setBounds(50,280,100,50);
		nameTextField = new JTextField();
		nameTextField.setBounds(50,320,200,30);
		
		//폰넘버
		pnumLabel = new JLabel("전화번호:");
		pnumLabel.setBounds(50,370,100,50);
		pnumTextField = new JTextField();
		pnumTextField.setBounds(50,410,200,30);
		
		//주소
		addressLabel =  new JLabel("주소:");
		addressLabel.setBounds(50,460,100,50);
		addressTextField =  new JTextField();
		addressTextField.setBounds(50,500,200,30);
		
		
		//회원가입 버튼
		joinButton = new JButton("회원가입");
		joinButton.setBounds(50,600,100,30);
		
		//취소 버튼
		exitButton = new JButton("취소");
		exitButton.setBounds(150,600,100,30);
		
		this.add(idTextField);
		this.add(idLabel);
		this.add(passTextField);
		this.add(passLabel);
		this.add(passTextFieldr);
		this.add(passLabelr);
		this.add(nameTextField);
		this.add(nameLabel);
		this.add(pnumTextField);
		this.add(pnumLabel);
		this.add(addressLabel);
		this.add(addressTextField);
		this.add(joinButton);
		this.add(exitButton);
		//this.add(mainP);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		joinButton.addActionListener(this);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == joinButton ) {
			
			char[] passTemp = passTextField.getPassword();
			String passString = new String(passTemp);
			
			char[] passTempr = passTextFieldr.getPassword();
			String passStringr = new String(passTempr);
			
			if(passString.equals(passStringr)) {
				Adm_DTO admdto = new Adm_DTO();
				String idTemp = idTextField.getText();
				//char[] passTemp = passTextField.getPassword();
				//String passString = new String(passTemp);
				
				String nameTemp = nameTextField.getText();
				String pnumTemp = pnumTextField.getText();
				String addressTemp = addressTextField.getText();
				
				admdto.setID(idTemp);
				admdto.setPassWord(passString);
				admdto.setName(nameTemp);
				admdto.setPhoneNumber(pnumTemp);
				admdto.setAddress(addressTemp);
				admdbdao.add(admdto);
			}else {
				JOptionPane.showMessageDialog(this, "비밀번호가 다릅니다", "비밀번호 재확인 실패", JOptionPane.ERROR_MESSAGE );
				
			}
			
			
			
		}
	}
	
	
	

}
