package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartUI extends JPanel {

	public StartUI() {
		ImageIcon img = new ImageIcon("InsLogo.png");
		JLabel lb1 = new JLabel("");
		lb1.setBounds(69, 109, 392, 116);
		lb1.setIcon(img);

		JButton btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("굴림", Font.BOLD, 20));
		btnLogin.setBounds(103, 257, 325, 37);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				LoginUI login = new LoginUI(); // 가져올 로그인 패널 객체 생성
				add(login);// 프레임 위에 로그인 패널 추가
				repaint();
			}
		});

		JButton btnRegister = new JButton("회원가입"); // 패널 위에 올릴 버튼
		btnRegister.setFont(new Font("굴림", Font.BOLD, 20));
		btnRegister.setBounds(103, 325, 325, 37);
		btnRegister.addActionListener(new ActionListener() { // 버튼 처리 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				RegisterUI register = new RegisterUI(); // 가져올 회원가입 패널 객체 생성
				add(register); // 프레임 위에 회원가입 패널 추가
				repaint();
			}
		});

		add(btnLogin); // 시작페이지 패널에 버튼 추가
		add(btnRegister); // 시작페이지 패널에 버튼 추가
		add(lb1);

		setBackground(Color.WHITE);
		setLayout(null);
		setSize(540, 560); // 프레임 사이즈
		setPreferredSize(new Dimension(505, 600));
		setVisible(true); // 프레임 보이게
	}
}
