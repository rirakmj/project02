package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.PublicKey;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.MemberDAO;
import vo.CustomerVO;

public class LoginPage extends JFrame 
	implements MouseListener, ActionListener, KeyListener {
	
	JPanel pbtn1, pbtn2, pbtn3, pbtn4, pbtn5, pbtn6, pbtn7;
	
	JTextField id;
	JPasswordField pwd;
	JButton btnCheck;
	
//	CustomerVO v; // 사용하지 않음.
	
//	String id2, pwd2; // 사용하지 않음.
	
	
	public LoginPage() {
		super("하이무루부시 예약 관리 시스템");
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		// panels 생성
		pbtn1 		= new JPanel();
		pbtn1.setBackground(new Color(255, 255, 255));
		pbtn1.setBounds(45, 57, 150, 68);
		pbtn1.setLayout(new CardLayout(0, 0));
		getContentPane().add(pbtn1);
		pbtn2 		= new JPanel();
		pbtn2.setBackground(new Color(255, 255, 255));
		pbtn2.setBounds(65, 176, 69, 38);
		getContentPane().add(pbtn2);
		pbtn3 		= new JPanel();
		pbtn3.setBackground(new Color(255, 255, 255));
		pbtn3.setBounds(203, 176, 200, 38);
		getContentPane().add(pbtn3);
		pbtn4 		= new JPanel();
		pbtn4.setBackground(new Color(255, 255, 255));
		pbtn4.setBounds(65, 236, 69, 38);
		getContentPane().add(pbtn4);
		pbtn5 		= new JPanel();
		pbtn5.setBackground(new Color(255, 255, 255));
		pbtn5.setBounds(203, 236, 200, 38);
		getContentPane().add(pbtn5);
		pbtn6 		= new JPanel();
		pbtn6.setBackground(Color.WHITE);
		pbtn6.setBounds(306, 314, 93, 40);
		getContentPane().add(pbtn6);
		pbtn7 		= new JPanel();
		pbtn7.setBackground(Color.WHITE);
		pbtn7.setBounds(230, 55, 240, 55);
		getContentPane().add(pbtn7);
				
		// 화면 상단에 제목 추가
		JLabel lbl1 = new JLabel("<html><b style= \"color:#484848\">하이무루부시<br>예약 현황</b><html>");
		lbl1.setBackground(new Color(255, 255, 255));
		lbl1.setVerticalAlignment(SwingConstants.TOP);
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pbtn1.add(lbl1, "name_7088402692029");
		pbtn2.setLayout(new CardLayout(0, 0));
		
		
		// 'ID' 글자 추가
		JLabel lbl_id = new JLabel("<html><b style=\"color:black\">ID<b></html>");
		lbl_id.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		pbtn2.add(lbl_id);
		
		
		// '아이디' 텍스트 박스 추가
		id = new JTextField(12);
		id.setText("아이디");
		id.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		id.setSize(100, 40);
		id.setForeground(Color.LIGHT_GRAY);
		id.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(id.getText().equals("아이디")) {
					id.setForeground(Color.darkGray);
				    id.setText("admin");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(id.getText().equals("")) {
					id.setForeground(Color.LIGHT_GRAY);
					id.setText("아이디");
				}
			}
		});
		
		pbtn3.add(id);
		pbtn4.setLayout(new CardLayout(0, 0));
		
		
		// '비밀번호' 글자 추가
		JLabel lbl_pw = new JLabel("<html><b style=\"color:black\">비밀번호<b></html>");
		lbl_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		pbtn4.add(lbl_pw);
		
		
		// '비밀번호' 텍스트 박스 추가
		pwd = new JPasswordField(12);
		pwd.setText("비밀번호");
		pwd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		pwd.setForeground(Color.LIGHT_GRAY);		
		pwd.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(pwd.getText().equals("비밀번호")) {
					pwd.setForeground(Color.darkGray);
					String a = "1234";
					pwd.setText(a);
//					pwd.setVisible(false);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(pwd.getText().equals("")) {
					pwd.setForeground(Color.LIGHT_GRAY);
					pwd.setText("비밀번호");
				}
			}
		});
		pbtn5.add(pwd);
		
		// '로그인' 버튼 추가
		btnCheck 	= new JButton("로그인");	
		btnCheck.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnCheck.setForeground(Color.WHITE);
		btnCheck.setBackground(new Color(240, 128, 128));
		btnCheck.setBorderPainted(false);
		pbtn6.add(btnCheck);
		
		// 이미지 추가
	    ImageIcon pic1 = new ImageIcon("./img/logo_1.png");
	    pbtn7.add(new JLabel(pic1));
	    this.add(pbtn7);
	    this.pack();
		
		// 이벤트 연결
		btnCheck.addActionListener(this);
		btnCheck.addKeyListener(this);
	
		// Default
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(473, 418);
		this.setLocation(20, 20);
		this.setResizable(false);
		this.setVisible(true);
		
		// Autofocus off
    	getContentPane().requestFocusInWindow();
		this.requestFocusInWindow();		
	}

	public static void main(String[] args) {
		new LoginPage();
	}
    
	@Override
	public void keyPressed(KeyEvent e) { 
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        Toolkit.getDefaultToolkit().beep(); 
        btnCheck.doClick();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventBtn = e.getSource();
		if(eventBtn==btnCheck) {
			getLoginData();
//			dispose();
//			new IntroList();
		}
	}
	public void getLoginData() {
		String value1 = id.getText();
		String value2 = pwd.getText();
		System.out.println("value1 값 : " +value1 );
		System.out.println("value2 값 : " +value2 );
		
//	    try {
	    	if(value1.equals("아이디")) {
//	    		System.out.println("아이디 메세지 출력 전");
	    		JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다.");
	    	} else if(value2.equals("비밀번호")) {
//	    		System.out.println("비번확인 메세지 출력 전");
	    		JOptionPane.showMessageDialog(this,  "비밀번호를 입력하셔야 합니다.");
	    	} else {
	    		if(!"admin".equals(value1) || !"1234".equals(value2)) {
	    		JOptionPane.showMessageDialog(this,  "아이디 또는 비밀번호가 틀렸습니다.");
	    	} else {
				 if(value1.equals("admin") && value2.equals("1234")) {
					dispose();
					Thread sta = new Thread(new IntroList());
				    sta.start();
				    }
	    	}
	    	}
	        }
	    	
//					} else {
//						new JOptionPane().showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
//					}
//				} else if(value2.equals("1234")) {
//					new JOptionPane().showMessageDialog(this, "아이디가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
//				} else {
//					new JOptionPane().showMessageDialog(this, "아이디와 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
//				}
			
//	    	} catch (Exception e1) {
//			System.out.println("에러가 발생했습니다");
//		}
//	}

	
//		MemberDao dao = new MemberDao();
//		v 		 	  = dao.getMember(value1);
		
//		id2  = v.getId();
//		pwd2  = v.getPwd();

//		try {
//			if( e.getSource() == btnCheck ) {
//					if (value1.equals(id2)) {
//						if(value2.equals(pwd2)) {
//							new IntroList();
//							dispose();
//							Thread sta = new Thread(new IntroList());
//						} else {
//							new JOptionPane().showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
//						}
//					} else if(value2.equals(pwd2)) {
//						new JOptionPane().showMessageDialog(this, "아이디가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
//					} else {
//						new JOptionPane().showMessageDialog(this, "아이디와 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
//					}
//			}
//		} catch (Exception e1) {
//			System.out.println("에러가 발생했습니다");
//		}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
