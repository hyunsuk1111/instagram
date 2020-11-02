package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.CommentRUD;
import model.NowUser;

public class CommentUI extends JPanel {
	JTextArea taCon;
	int cNo;

	public CommentUI(int pNo) {

		taCon = new JTextArea();
		taCon.setBounds(10, 10, 440, 50);

		JButton btnCreate = new JButton("POST");
		btnCreate.setBounds(450, 10, 60, 50);
		btnCreate.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorder(null);
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String date = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				String cCon = taCon.getText();

				if (!cCon.matches("")) {
					CommentRUD rud = new CommentRUD();
					if (cNo == 0) {
						rud.createComment(cCon, date, SaveUserInfo.getloginID(), pNo);
					} else {
						rud.updatePost(cNo, cCon, date);
					}

					taCon.setText(null);
				} else {
					JOptionPane.showMessageDialog(getParent(), "No Content!");
				}
			}
		});

		add(btnCreate);
		add(taCon);

		setLayout(null);
		setSize(540, 70);
		setPreferredSize(new Dimension(540, 70));
	}
}
