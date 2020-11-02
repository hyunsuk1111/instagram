package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ctrl.MainFrame;
import db.DBCon;
import model.UserDAO;
import model.UserVO;
import util.FileSave;

public class MyInfoUpdateUI extends JPanel { // 내피드 > 개인정보수정

	// DB 연결
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public MyInfoUpdateUI() {
		JLabel lb2 = new JLabel("아이디");
		lb2.setFont(new Font("굴림", Font.BOLD, 17));
		lb2.setBounds(161, 2, 73, 45);

		JLabel lb3 = new JLabel("현재 비밀번호");
		lb3.setFont(new Font("굴림", Font.BOLD, 17));
		lb3.setBounds(161, 84, 125, 45);

		JLabel lb4 = new JLabel("변경할 비밀번호");
		lb4.setFont(new Font("굴림", Font.BOLD, 17));
		lb4.setBounds(162, 160, 180, 45);

		JLabel lb6 = new JLabel("전화번호");
		lb6.setFont(new Font("굴림", Font.BOLD, 17));
		lb6.setBounds(163, 308, 107, 45);

		JTextField tfID = new JTextField(10);
		tfID.setFont(new Font("굴림", Font.PLAIN, 20));
		tfID.setBackground(Color.WHITE);
		tfID.setColumns(10);
		tfID.setBounds(163, 50, 202, 31);
		tfID.setText(SaveUserInfo.getloginID()); // SaveUserInfo class에서 전달받은 uID를 tfid 텍스트필드에 저장
		tfID.setFocusable(false);

		JTextField tfPhone = new JTextField(10);
		tfPhone.setFont(new Font("굴림", Font.PLAIN, 20));
		tfPhone.setBackground(Color.WHITE);
		tfPhone.setColumns(10);
		tfPhone.setBounds(163, 342, 202, 31);
		UserDAO dao = new UserDAO(); // UserDAO 생성
		try {
			// 현재 로그인한 ID를 인자로 GetUserPhone에 전달
			tfPhone.setText(dao.getUserPhone(SaveUserInfo.getloginID()));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JPasswordField tfPW = new JPasswordField();
		tfPW.setBounds(163, 119, 202, 31);

		JPasswordField tfCpw = new JPasswordField();
		tfCpw.setBounds(163, 196, 202, 31);

		JLabel lb5 = new JLabel("변경할 비밀번호 확인");
		lb5.setFont(new Font("굴림", Font.BOLD, 17));
		lb5.setBounds(161, 237, 180, 45);

		JPasswordField tfCpwC = new JPasswordField();
		tfCpwC.setBounds(162, 273, 201, 31);

		JButton btnUpdate = new JButton("수정완료");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = 1; // 텍스트 필드 초기화를 위한 논리변수
				UserDAO dao = new UserDAO(); // UserDAO 객체 생성
				UserVO vo = new UserVO(); // UserVo 객체 생성
				String uID = tfID.getText(); // tfID 텍스트필드 값 가져오기
				String uPW = String.valueOf(tfPW.getPassword()); // tfPW 텍스트필드 값 가져오기
				String cPW = String.valueOf(tfCpw.getPassword()); // 변경할 비번
				String cPWC = String.valueOf(tfCpwC.getPassword()); // 변경할 비번 확인
				String uPhone = tfPhone.getText();

				// 가방에 데이터 담기
				if (uPW.equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요");
				} else {
					vo.setuID(uID);
					vo.setuPW(uPW);
					vo.setcPW(cPW);
					vo.setcPWC(cPWC);
					vo.setuPhone(uPhone);
					try {
						check = dao.update(vo); // DAO 수정메서드 호출 및 논리변수에 값 저장
						if (check == 0) { // 수정 완료 시 비밀번호,변경할비밀번호,체크 초기화
							tfPW.setText("");
							tfCpw.setText("");
							tfCpwC.setText("");
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnUpdate.setBounds(109, 404, 143, 38);

		JButton btnDelete = new JButton("회원탈퇴");
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				UserDAO dao = new UserDAO(); // dao 객체생성
				String uID = tfID.getText();
				try {
					// 버튼 메시지
					String[] options = new String[] { "확인", "취소" };
					// 비밀번호 입력칸 생성
					JPasswordField ps = new JPasswordField();
					int pc = JOptionPane.showOptionDialog(null, ps, "비밀번호를 입력하세요", JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
					if (pc == 0) { // 확인 버튼 클릭 시
						String uPW = String.valueOf(ps.getPassword()); // 패스워드 필드에 있는 값 가져오기
						dao.delete(uID, uPW); // UserDAO 삭제 메서드 호출
					} else { // 취소 버튼 클릭시
						JOptionPane.showMessageDialog(null, "취소되었습니다.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnDelete.setBounds(109, 471, 143, 38);

		JButton btnNewButton = new JButton("유저정보저장");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					UserDAO dao = new UserDAO();
					FileSave fs = new FileSave();
					// 리스트에 유저정보 vo 저장
					ArrayList<UserVO> list = dao.getUser();
					// 파일 저장 메서드 호출
					fs.save(list);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		btnNewButton.setBounds(267, 472, 143, 38);

		JButton btnUpdate_1 = new JButton("로그아웃");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 현재 아이디와 패스워드 null로 초기화 하여 가방에 저장
				SaveUserInfo.setloginID(null);

				System.out.println("로그아웃되었습니다.");
				System.out.println("현재ID: " + SaveUserInfo.getloginID());

				removeAll();
				MainFrame.f.showPnStart(1);
			}
		});
		btnUpdate_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnUpdate_1.setBounds(267, 404, 143, 38);

		add(lb2);
		add(lb3);
		add(lb4);
		add(lb6);
		add(tfID);
		add(tfPhone);
		add(tfPW);
		add(tfCpw);
		add(lb5);
		add(tfCpwC);
		add(btnUpdate);
		add(btnDelete);

		add(btnNewButton);
		add(btnUpdate_1);

		setBackground(Color.WHITE);
		setLayout(null);
		setSize(540, 560);
		setPreferredSize(new Dimension(505, 560));
		setVisible(true); // 제일 마지막
	}
}
