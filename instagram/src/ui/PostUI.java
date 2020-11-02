package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ctrl.MainFrame;
import model.NowUser;
import model.PostDAO;
import model.PostDTO;

public class PostUI extends JPanel {
	boolean fileExist; // 파일 선택여부
	File selectF;
	String selectFName;
	String selectFLoc;
	Image resizeImg;

	public PostUI(MainFrame f) {
		final String IMG_LOC = "C:/Users/pc_jw/Desktop/cording/eclipse/Workspace/Workspace/project1/icon/"; // 이미지 위치
		JFileChooser fc = new JFileChooser();

		JPanel pnImg = new JPanel();
		pnImg.setBackground(new Color(255, 250, 205));
		pnImg.setBounds(17, 0, 480, 480);

		JLabel lbShowImg = new JLabel();
		lbShowImg.setBackground(new Color(255, 250, 205));
		lbShowImg.setBounds(17, 0, 480, 480);

		JPanel pnCon = new JPanel();
		pnCon.setBounds(4, 490, 500, 250);
		pnCon.setBackground(Color.white);

		JTextArea taCon = new JTextArea();
		taCon.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		taCon.setBounds(14, 0, 480, 140);
		taCon.setBackground(new Color(251,255,249));
		taCon.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

		JButton btnImg = new JButton("Select a image");
		btnImg.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnImg.setBounds(14, 155, 480, 45);
		btnImg.setContentAreaFilled(false);
		btnImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(PostUI.this); // fc 선택 결과
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectF = fc.getSelectedFile(); // 현재 선택한 file
					selectFName = selectF.getName(); // file name
					selectFLoc = selectF.getAbsolutePath(); // file location

					try {
						BufferedImage img = ImageIO.read(selectF); // 선택한 파일을 이미지로 읽어들이기
						resizeImg = img.getScaledInstance(480, 480, Image.SCALE_SMOOTH);

						lbShowImg.setIcon(new ImageIcon(resizeImg)); // 선택한 파일 저장 위치에서 불러오기
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					fileExist = true; // 파일이 선택되었으면(인스타는 무조건 사진 선택해야 글 올릴 수 있음
				} else {
					JOptionPane.showMessageDialog(pnImg, "Cancelled.");
				}
			}
		});

		JButton btnAdd = new JButton("Post");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnAdd.setBounds(14, 205, 480, 45);
		btnAdd.setContentAreaFilled(false);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileExist) {
					try {
						PostDTO dto = new PostDTO();
						PostDAO dao = new PostDAO();
						String pCDate = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

						BufferedImage saveImg = new BufferedImage(480, 480, BufferedImage.TYPE_INT_BGR);
						saveImg.createGraphics().drawImage(resizeImg, 0, 0, PostUI.this);

						String imgName = SaveUserInfo.getloginID() + "_" + pCDate + "_" + selectFName;
						File saveFile = new File(IMG_LOC, imgName);
						ImageIO.write(saveImg, "jpg", saveFile);

						String pCon = taCon.getText();
						String pImg = String.valueOf(saveFile);

						dto.setpCon(pCon);
						dto.setpImg(pImg);
						dto.setpCDate(pCDate);
						dto.setuID(SaveUserInfo.getloginID());
						dao.create(dto);

						JOptionPane.showMessageDialog(pnCon, "Completed.");
						f.showHome();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(pnCon, "No image selected!");
				}
			}
		});

		pnImg.add(lbShowImg);
		pnCon.setLayout(null);
		pnCon.add(taCon);
		pnCon.add(btnImg);
		pnCon.add(btnAdd);

		setLayout(null);
		add(pnImg);
		add(pnCon);

		setBackground(Color.white);
		setSize(506, 740);
		setPreferredSize(new Dimension(506, 740));
		setVisible(true);
	}
}
