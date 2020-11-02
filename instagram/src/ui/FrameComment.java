package ui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FrameComment extends JFrame {
	static public FrameComment fc;
	
	public FrameComment(int pNo) {
		JPanel pnBg = new JPanel(); // 배경 패널
		pnBg.setLayout(new BoxLayout(pnBg, BoxLayout.Y_AXIS));
		
		JScrollPane scroll = new JScrollPane(pnBg); // 스크롤패널에 배경 패널 얹기
		scroll.setHorizontalScrollBar(null); // 가로 스크롤 막기

		AllCommentUI pnAllComent = new AllCommentUI(pNo); // 전체 댓글 패널 객체 생성
		if (pnAllComent.height == 0) { // 댓글이 하나도 없으면, 빈 화면이 아니라 없는 거라고 알려줌
			pnBg.add(new JLabel("hey you have no comment"));
		}
		pnAllComent.setPreferredSize(new Dimension(520, pnAllComent.height));
		
		pnBg.add(pnAllComent);

		add(scroll);

		setSize(540, 560);
		setVisible(true);
	}
}
