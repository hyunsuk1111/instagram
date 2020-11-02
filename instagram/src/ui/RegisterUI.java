package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.UserDAO;
import model.UserVO;

public class RegisterUI extends JPanel {

	boolean result1; // 중복확인을 위한 논리변수

	public RegisterUI() {
		// dao 객체 생성
		UserDAO db = new UserDAO();
		// VO 객체 생성
		UserVO vo = new UserVO();
		// 회원가입 시간 객체 생성
		Date time = new Date();
		// 시간 포맷 변환
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String time1 = format1.format(time);

		JLabel lb2 = new JLabel("아이디");
		lb2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lb2.setBounds(157, 86, 73, 45);

		JLabel lb3 = new JLabel("비밀번호");
		lb3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lb3.setBounds(157, 157, 89, 45);

		JLabel lb4 = new JLabel("비밀번호 확인");
		lb4.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lb4.setBounds(157, 240, 126, 45);

		JLabel lb5 = new JLabel("전화 번호");
		lb5.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lb5.setBounds(157, 321, 107, 45);

		JTextField tfID = new JTextField(10);
		tfID.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		tfID.setBackground(Color.WHITE);
		tfID.setColumns(10);
		tfID.setBounds(158, 126, 222, 31);

		JTextField tfPNum = new JTextField(10);
		tfPNum.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		tfPNum.setBackground(Color.WHITE);
		tfPNum.setColumns(10);
		tfPNum.setBounds(157, 356, 222, 31);

		JPasswordField tfPW = new JPasswordField();
		tfPW.setBounds(158, 194, 222, 31);

		JPasswordField tfPWC = new JPasswordField();
		tfPWC.setBounds(158, 275, 222, 31);

		JButton btnNewButton = new JButton("중복 확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO dao = new UserDAO(); // dao 객체 생성
				String uID = tfID.getText(); // tfID 텍스트필드의 데이터값 저장

				try {
					// 데이터 입력 후 중복확인 버튼 누를 시
					if (!tfID.getText().equals("")) { // tfID 텍스트 필드 값이 있으면(빈칸이 아니면)
						result1 = dao.CheckID(uID); // UserDAO CheckID 메서드 호출 및 논리값 저장
						if (result1) { // ID 중복 이면 텍스트필드 초기화
							tfID.setText("");
							JOptionPane.showMessageDialog(RegisterUI.this, "사용 불가능");
						} else {
							result1 = true;
							JOptionPane.showMessageDialog(RegisterUI.this, "사용 가능");
							tfID.setFocusable(false); // 사용 가능 시 수정못하게 설정
						}
					} else { // tfID 가 빈칸이면
						JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(284, 95, 89, 23);
		add(btnNewButton);

		JButton btnRegister = new JButton("회원 가입");
		btnRegister.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnRegister.setBounds(181, 469, 168, 38);
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 유효성 검사 - 빈칸,비밀번호 / 검사 후 저장
				if (tfID.getText().trim().isEmpty()) { // 아이디 미입력시
					JOptionPane.showMessageDialog(RegisterUI.this, "아이디 미입력");
				} else {
					// 비밀번호가 빈칸이면
					if (String.valueOf(tfPW.getPassword()).trim().isEmpty()) {
						JOptionPane.showMessageDialog(RegisterUI.this, "비밀번호 미입력");
					} else { // 비밀번호확인이 빈칸이면
						if (String.valueOf(tfPWC.getPassword()).trim().isEmpty()) {
							JOptionPane.showMessageDialog(RegisterUI.this, "비밀번호 확인 미입력");
						} else { // 비밀번호 불일치 시
							if (!(String.valueOf(tfPW.getPassword()).equals(String.valueOf(tfPWC.getPassword())))) {
								JOptionPane.showMessageDialog(RegisterUI.this, "비밀번호가 불일치");
							} else { // 전화번호 빈칸이면
								if (tfPNum.getText().trim().isEmpty()) {
									JOptionPane.showMessageDialog(RegisterUI.this, "전화번호 미입력");
								} else {
									// 유효성검사 마치고 변수에 저장
									String uID = tfID.getText();
									String uPW = String.valueOf(tfPW.getPassword());
									String uPhone = tfPNum.getText();
									String uCDate = time1;
									// 변수들 vo 가방에 저장하기

									if (result1) { // 중복 확인 했으면 > 아이디 사용 가능이면
										vo.setuID(uID); // UserVO에 데이터 저장
										vo.setuPW(uPW);
										vo.setuPhone(uPhone);
										vo.setuCDate(uCDate);
										try {
											db.create(vo); // UserDAO create 메서드 호출
											JOptionPane.showMessageDialog(RegisterUI.this, "회원가입 완료 ");
											// 회원 가입 완료시 회원가입패널 삭제,login 패널 출력
											removeAll();
											LoginUI login = new LoginUI();
											add(login);
											repaint();
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									} else { // 중복확인 안했으면
										JOptionPane.showMessageDialog(RegisterUI.this, "중복버튼을 눌러주세요");
									}
								}
							}
						}
					}
				}
			}
		});

		add(lb2);
		add(tfID);
		add(lb3);
		add(lb3);
		add(tfPW);
		add(lb4);
		add(tfPWC);
		add(tfPNum);
		add(lb5);
		add(btnRegister);

		setSize(540, 560);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
	}
}
