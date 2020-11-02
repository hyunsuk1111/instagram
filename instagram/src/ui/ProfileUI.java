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

import ctrl.MainFrame;
import model.NowUser;
import model.PostDAO;

public class ProfileUI extends JPanel {

	public ProfileUI() {
		final String IC_LOC = "C:/Users/pc_jw/Desktop/cording/eclipse/Workspace/Workspace/project1/icon/"; // 이미지 위치

		JLabel lbProfile = new JLabel(new ImageIcon(IC_LOC + "icPerson.png"));
		lbProfile.setBounds(2, 0, 64, 64);

		JLabel lbIntro = new JLabel("Life is short...you need JAVA");
		lbIntro.setBounds(12, 50, 200, 64);
		lbIntro.setFont(new Font("맑은고딕", Font.BOLD, 15));

		JLabel lbCount = new JLabel(new PostDAO().readCount(SaveUserInfo.getloginID()) + "");
		lbCount.setBounds(450, 15, 30, 30);
		lbCount.setFont(new Font("맑은 고딕", 0, 24));
		lbCount.setBackground(Color.yellow);

		JLabel lbText = new JLabel("POST");
		lbText.setBounds(437, 37, 60, 30);
		lbText.setFont(new Font("맑은 고딕", 0, 14));
		lbText.setBackground(Color.yellow);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(10, 100, 482, 25);
		btnEdit.setContentAreaFilled(false);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.f.showMyInfo(); // 개인정보 수정화면으로 이동
			}
		});

		add(lbIntro);
		add(btnEdit);
		add(lbCount);
		add(lbText);
		add(lbProfile);
		
		setLayout(null);
		setSize(505, 110);
		setBackground(Color.white);
		setPreferredSize(new Dimension(505, 110));
		setVisible(true);
	}
}
