package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoUI extends JPanel {

	public LogoUI(String title) {

		JLabel lbLogo = new JLabel(new ImageIcon("C:/Users/pc_jw/Desktop/cording/eclipse/Workspace/Workspace/project1/icon/icLogo.png/"));
		lbLogo.setBounds(0, 0, 70, 70);
		
		JLabel lbText = new JLabel(title);
		lbText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbText.setBounds(80, -5, 200, 80);
		
		add(lbText);
		add(lbLogo);
		
		setLayout(null);
		setBackground(Color.WHITE);
		setSize(525, 65);
		setPreferredSize(new Dimension(525, 65));
		setVisible(true);
	}
}
