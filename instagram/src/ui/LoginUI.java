package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.UserDAO;
import model.UserVO;

public class LoginUI extends JPanel {
	private static JTextField tfID;

	public LoginUI() {
		setBackground(Color.WHITE);

		tfID = new JTextField();
		tfID.setBounds(163, 169, 222, 31);
		tfID.setColumns(10);
		JPasswordField tfPW = new JPasswordField();
		tfPW.setBounds(163, 268, 222, 31);

		JButton btnLogin = new JButton("로그인");
		btnLogin.setBounds(163, 381, 210, 38);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dao 객체 생성
				UserDAO dao = new UserDAO();
				// vo 객체 생성
				UserVO vo = new UserVO();
				// 빈칸 체크,제거(isEmpty,trim)
				if (tfID.getText().trim().isEmpty()) { // 아이디 미입력시
					JOptionPane.showMessageDialog(LoginUI.this, "아이디 미입력");
				} else {
					if (String.valueOf(tfPW.getPassword()).trim().isEmpty()) { // 비밀번호 미입력시
						JOptionPane.showMessageDialog(LoginUI.this, "비밀번호 미입력");
					} else {
						String uID = tfID.getText(); // 모두 입력시 텍스트필드에 있는 데이터 가져오기
						String uPW = String.valueOf(tfPW.getPassword());
						// vo객체에 데이터 저장
						vo.setuID(uID);
						vo.setuPW(uPW);
						try {
							// 로그인
							dao.read(vo);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		JButton btnRegister = new JButton("회원가입");
		btnRegister.setBounds(163, 441, 210, 38);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				RegisterUI register = new RegisterUI();
				add(register);
				repaint();
			}
		});
		
		JLabel lb2 = new JLabel("아이디");
		lb2.setFont(new Font("굴림", Font.BOLD, 17));
		lb2.setBounds(163, 137, 57, 31);
		
		JLabel lb3 = new JLabel("비밀번호");
		lb3.setFont(new Font("굴림", Font.BOLD, 17));
		lb3.setBounds(163, 241, 91, 23);

		add(btnRegister);
		add(btnLogin);
		add(lb2);
		add(lb3);
		add(tfID);
		add(tfPW);
		setSize(540, 740);
		setLayout(null);
		setVisible(true);

	}

}
