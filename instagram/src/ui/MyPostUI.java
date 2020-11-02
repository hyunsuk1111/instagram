package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ctrl.MainFrame;
import model.AllPostRUD;
import model.CommentDAO;
import model.HeartDAO;
import model.HeartDTO;
import model.PostDAO;
import model.PostDTO;
import util.DateSlicer;

public class MyPostUI extends JPanel {

	AllPostRUD rud = new AllPostRUD();
	public int height;

	public MyPostUI() {
		try {
			PostDAO pDao = new PostDAO();
			ArrayList<PostDTO> postList = pDao.read(SaveUserInfo.getloginID());

			height = postList.size() * 730;

			for (int i = 0; i < postList.size(); i++) {
				PostDTO dto = postList.get(i);
				add(addPanel(dto.getpNo(), dto.getpImg(), dto.getpCon(), DateSlicer.slice(dto.getpCDate()), dto.getpUDate(), dto.getpHeart(),
						dto.getuID()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		setSize(505, 630);
		setPreferredSize(new Dimension(505, 730));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public JPanel addPanel(int pNo, String pImg, String pCon, String uCDate, String pUDate, int pHeart, String uID) {
		final String IC_LOC = "C:/Users/pc_jw/Desktop/cording/eclipse/Workspace/Workspace/project1/icon/"; // 이미지 위치
		JButton btnDoUpdate = new JButton();
		JButton btnNoUpdate = new JButton();
		ImageIcon icUnheart = new ImageIcon(IC_LOC + "icUnheart.png");
		ImageIcon icHeart = new ImageIcon(IC_LOC + "icHeart.png");
		HeartDAO hDao = new HeartDAO();

		JPanel pnPost = new JPanel();
		pnPost.setBackground(Color.white);
		pnPost.setLayout(null);
		pnPost.setSize(500, 730);
		pnPost.setPreferredSize(new Dimension(500, 730));

		Font font = new Font("맑은 고딕", 0, 12);
		Font font2 = new Font("맑은 고딕", 0, 9);
		Font font3 = new Font("맑은 고딕", Font.BOLD, 13);
		Font font4 = new Font("맑은 고딕", 0, 14);

		JLabel lbProfile = new JLabel(new ImageIcon(IC_LOC + "icPerson.png"));
		lbProfile.setBounds(10, 10, 48, 48);

		JLabel lbCDate = new JLabel();
		lbCDate.setText(uCDate);
		lbCDate.setBounds(67, 17, 150, 12);
		lbCDate.setFont(font);
		
		JLabel lbsetUp = new JLabel();
		lbsetUp.setBounds(200, 16, 150, 14);
		lbsetUp.setFont(font);
		lbsetUp.setForeground(Color.gray);
		if (pUDate != null) {
			lbsetUp.setText("수정됨");
			pnPost.add(lbsetUp);
			pnPost.repaint();
		}

		JLabel lbUID = new JLabel();
		lbUID.setText(uID);
		lbUID.setBounds(67, 30, 100, 20);
		lbUID.setFont(font3);

		JLabel lbImg = new JLabel();
		lbImg.setIcon(new ImageIcon(pImg));
		lbImg.setBounds(10, 60, 480, 480);

		JTextArea taCon = new JTextArea();
		taCon.setText(pCon);
		taCon.setBounds(15, 605, 470, 60);
		taCon.setFont(font);
		taCon.setFocusable(false);

		btnDoUpdate.setBounds(354, 673, 60, 28);
		btnDoUpdate.setFont(font2);
		btnDoUpdate.setText("Done");
		btnDoUpdate.setBackground(null);
		btnDoUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				String pUDate = String.valueOf(format.format(new Date()));

				rud.updatePost(pNo, taCon.getText(), pUDate);

				taCon.setFocusable(false);
				taCon.setBorder(null);
				pnPost.remove(btnDoUpdate);
				pnPost.remove(btnNoUpdate);
				pnPost.repaint();
			}
		});

		btnNoUpdate.setBounds(419, 673, 63, 28);
		btnNoUpdate.setFont(font2);
		btnNoUpdate.setText("Cancel");
		btnNoUpdate.setBackground(null);
		btnNoUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taCon.setFocusable(false);
				taCon.setBorder(null);
				pnPost.remove(btnDoUpdate);
				pnPost.remove(btnNoUpdate);
				pnPost.repaint();
			}
		});

		JButton btnUpdate = new JButton();
		btnUpdate.setIcon(new ImageIcon(IC_LOC + "icUpdate2.png"));
		btnUpdate.setBounds(420, 20, 40, 40);
		btnUpdate.setBackground(null);
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnPost, "Update?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					pnPost.add(btnDoUpdate);
					pnPost.add(btnNoUpdate);
					taCon.setFocusable(true);
					taCon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					pnPost.repaint();
				} else if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(pnPost, "cancelled");
				}
			}
		});

		JButton btnDelete = new JButton();
		btnDelete.setIcon(new ImageIcon(IC_LOC + "icDelete2.png"));
		btnDelete.setBounds(450, 20, 40, 40);
		btnDelete.setBackground(null);
		btnDelete.setBorder(null);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnPost, "Delete?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					rud.deletePost(pNo);
//					removeAll();
//					Frame.f.showMe();
				} else if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(pnPost, "cancelled");
				}
			}
		});

		JLabel lbHeart = new JLabel();
		lbHeart.setBounds(17, 568, 150, 45);
		lbHeart.setText(pHeart + "명이 좋아합니다");
		lbHeart.setFont(font);

		JButton btnHeart = new JButton();
		if (hDao.read(pNo, SaveUserInfo.getloginID())) { // 좋아요 누른 게시글
			btnHeart.setIcon(icHeart);
		} else { // 안 누른 게시글
			btnHeart.setIcon(icUnheart);
		}
		btnHeart.setBounds(10, 542, 38, 38);
		btnHeart.setBackground(null);
		btnHeart.setBorder(null);
		btnHeart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PostDAO pDao = new PostDAO();
				HeartDTO hDto = new HeartDTO();
				int pHeart = 0;

				if (btnHeart.getIcon() == icUnheart) {
					btnHeart.setIcon(icHeart);

					pHeart = pDao.update(pNo, 1);

					hDto.setpNo(pNo);
					hDto.setuID(SaveUserInfo.getloginID());
					hDao.create(hDto);

					lbHeart.setText(pHeart + "명이 좋아합니다");
					pnPost.repaint();
				} else if (btnHeart.getIcon() == icHeart) {
					btnHeart.setIcon(icUnheart);

					pHeart = pDao.update(pNo, -1);

					hDto.setpNo(pNo);
					hDto.setuID(SaveUserInfo.getloginID());
					hDao.create(hDto);

					lbHeart.setText(pHeart + "명이 좋아합니다");
					pnPost.repaint();
				}
			}
		});

		JButton btnComment = new JButton();
		btnComment.setIcon(new ImageIcon(IC_LOC + "icComment.png"));
		btnComment.setBounds(48, 542, 38, 38);
		btnComment.setFont(font4);
		btnComment.setText(String.valueOf(new CommentDAO().read("pNo", pNo).size()));
		btnComment.setHorizontalTextPosition(JButton.CENTER);
		btnComment.setVerticalTextPosition(JButton.CENTER);
		btnComment.setBackground(null);
		btnComment.setBorder(null);
		btnComment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameComment comment = new FrameComment(pNo);
				comment.setVisible(true);
			}
		});
		
		if (uID.matches(SaveUserInfo.getloginID())) { // 내 게시글만 수정, 삭제 버튼 보임!
			pnPost.add(btnUpdate);
			pnPost.add(btnDelete);
		}

		pnPost.add(lbProfile);
		pnPost.add(lbUID);
		pnPost.add(btnUpdate);
		pnPost.add(btnDelete);
		pnPost.add(lbImg);
		pnPost.add(lbCDate);
		pnPost.add(taCon);
		pnPost.add(lbHeart);
		pnPost.add(btnHeart);
		pnPost.add(btnComment);

		return pnPost;
	}
	
	public void change() {
		
		
		
		
		
	}
}
