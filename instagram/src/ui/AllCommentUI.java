package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.CommentDAO;
import model.CommentDTO;
import model.CommentRUD;
import model.NowUser;
import util.DateSlicer;

public class AllCommentUI extends JPanel {
	CommentRUD rud = new CommentRUD();
	CommentDAO dao = new CommentDAO();
	ArrayList<CommentDTO> commentList;
	CommentUI com;
	CommentDTO dto;
	public int height; // 패널 높이 구하려고 만듦

	public AllCommentUI(int pNo) {
		commentList = dao.read("pNo", pNo);

		height = commentList.size() * 100;

		for (int i = 0; i < commentList.size(); i++) {
			dto = commentList.get(i);
			add(addPanel(dto.getcNo(), dto.getcCon(), DateSlicer.slice(dto.getcCDate()), dto.getcUDate(), dto.getuID(),
					dto.getpNo()));
		}
		com = new CommentUI(pNo);

		add(com);
		setSize(540, 100);
		setPreferredSize(new Dimension(505, 100));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public JPanel addPanel(int cNo, String cCon, String cCDate, String cUDate, String uID, int pNo) {
		final String IC_LOC = "C:/Users/pc_jw/Desktop/cording/eclipse/Workspace/Workspace/project1/icon/"; // 이미지 위치

		JPanel pnComment = new JPanel();
		pnComment.setBackground(Color.white);
		pnComment.setLayout(null);
		pnComment.setSize(500, 90);
		pnComment.setPreferredSize(new Dimension(500, 90));

		Font font = new Font("맑은 고딕", Font.PLAIN, 15);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 15);
		Font font3 = new Font("맑은 고딕", Font.PLAIN, 12);

		JLabel lbProfile = new JLabel();
		lbProfile.setBounds(10, 10, 55, 55);
		lbProfile.setIcon(new ImageIcon(IC_LOC + "icPerson.png"));

		JLabel lbUID = new JLabel();
		lbUID.setBounds(75, 15, 100, 20);
		lbUID.setFont(font2);
		lbUID.setText(uID);

		JLabel lbCon = new JLabel();
		lbCon.setBounds(75, 27, 400, 40);
		lbCon.setFont(font);
		lbCon.setText(cCon);

		JLabel lbCDate = new JLabel();
		lbCDate.setBounds(75, 60, 150, 20);
		lbCDate.setFont(font3);
		lbCDate.setText(cCDate);

		JButton btnUpdate = new JButton();
		btnUpdate.setIcon(new ImageIcon(IC_LOC + "icUpdate2.png"));
		btnUpdate.setBounds(440, 10, 30, 30);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnComment, "Update?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					commentList = dao.read("cNo", cNo);

					for (int i = 0; i < commentList.size(); i++) {
						dto = commentList.get(i);
						String con = dto.getcCon();
						com.cNo = cNo; // 현재 선택한 댓글 번호 세팅
						com.taCon.setText(con);
						// 새고...하고 싶다
					}
				} else if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(pnComment, "cancle");
				}
			}
		});

		JButton btnDelete = new JButton();
		btnDelete.setIcon(new ImageIcon(IC_LOC + "icDelete2.png"));
		btnDelete.setBounds(470, 10, 30, 30);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorder(null);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnComment, "Delete?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					rud.deletePost(cNo);
					// 새고...하고 싶다
				} else if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(pnComment, "cancle");
				}
			}
		});

		if (uID.matches(SaveUserInfo.getloginID())) {
			pnComment.add(btnUpdate);
			pnComment.add(btnDelete);
		}
		
		pnComment.add(lbProfile);
		pnComment.add(lbUID);
		pnComment.add(lbCon);
		pnComment.add(lbCDate);

		return pnComment;
	}
}
