package ctrl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.AllPostUI;
import ui.LogoUI;
import ui.MyInfoUpdateUI;
import ui.MyPostUI;
import ui.PostUI;
import ui.ProfileUI;
import ui.StartUI;

public class MainFrame extends JFrame {

	static public MainFrame f;
	JPanel pnBg;
	JScrollPane scroll;
	LogoUI pnLogo;
	public LogoUI pnLogo2;
	ProfileUI pnProfile;
	JButton btnMe;
	JButton btnHome;
	JButton btnPost;
	MyInfoUpdateUI pnEdit;
	public MyPostUI myPost;

	public static void main(String[] args) {
		f = new MainFrame();
	}

	public MainFrame() {
		final String IC_LOC = "C:/Users/pc_jw/Desktop/cording/eclipse/Workspace/Workspace/project1/icon/"; // 이미지 위치
		pnLogo = new LogoUI("INSTAGRAM"); // 기본로고(인스타그램 텍스트)

		pnBg = new JPanel(); // 배경 패널
		pnBg.setBackground(Color.white);
		scroll = new JScrollPane(pnBg); // 배경 패널에 스크롤 패널 얹기
		scroll.setHorizontalScrollBar(null); // 가로 스크롤 막기
		scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 및 크기
		scroll.setBorder(null); // 테두리 안 어울려서 없애줬음
		scroll.getVerticalScrollBar().setUnitIncrement(20); // 스크롤 속도
		scroll.getVerticalScrollBar().setValue(0); // 스크롤 맨 위로

		showPnStart(0); // 초기 진입 화면

		btnHome = new JButton(); // 메뉴1
		btnHome.setBounds(10, 855, 160, 60);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorder(null);
		btnHome.setIcon(new ImageIcon(IC_LOC + "icHome2.png"));
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showHome();
			}
		});

		btnPost = new JButton(); // 메뉴2
		btnPost.setBounds(180, 855, 160, 60);
		btnPost.setContentAreaFilled(false);
		btnPost.setBorder(null);
		btnPost.setIcon(new ImageIcon(IC_LOC + "icPosting.png"));
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showPoting(f);
			}
		});

		btnMe = new JButton(); // 메뉴3
		btnMe.setBounds(350, 855, 160, 60);
		btnMe.setContentAreaFilled(false);
		btnMe.setBorder(null);
		btnMe.setIcon(new ImageIcon(IC_LOC + "icPerson4.png"));
		btnMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showMe();
			}
		});

		add(scroll);

		setLayout(null);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setSize(540, 960);
		setVisible(true);
	}

	public void showPnStart(int status) { // 초기 진입화면 & 로그아웃하고
		if (status == 1) {
			pnBg.remove(pnEdit);
			remove(pnLogo2);
			remove(btnHome);
			remove(btnMe);
			remove(btnPost);
//			scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 다시 잡아주고
		}
		add(pnLogo);
		StartUI pnStart = new StartUI(); // 초기화면 생성
		pnStart.setPreferredSize(new Dimension(540, 560));
		pnBg.add(pnStart);
		pnBg.repaint();
		repaint();
	}

	// 메인화면 버튼 3개(전체 피드, 포스팅, 내 피드)
	public void showHome() { // 첫번째 화면(전체 피드)
		add(pnLogo); // 기본로고만 보이게
		remove(pnLogo2);
		if (pnProfile != null) {
			remove(pnProfile);
		}
		repaint();

		pnBg.removeAll(); // 기존 패널 컴포넌트 삭제
		scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 다시 잡아주고
		scroll.getVerticalScrollBar().setValue(0); // 맨위로 올리고(근데 안 먹히는 것 같아..)

		AllPostUI allPost = new AllPostUI(); // 전체 피드 패널 객체 생성
		if (allPost.height == 0) { // 글이 하나도 없으면, 화면에 안 뜬게 아니라 보여줄게 없다고 알림
			pnBg.add(new JLabel("hey you have no post"));
		}
		allPost.setPreferredSize(new Dimension(507, allPost.height)); // 면적 잡아주고
		pnBg.add(allPost);
		pnBg.repaint();
	}

	public void showPoting(MainFrame f) { // 두번째 화면(포스팅)
		pnBg.removeAll(); // 기존 패널 컴포넌트 삭제
		remove(pnLogo2);
		if (pnProfile != null) {
			remove(pnProfile);
		}
		repaint();

		scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 다시 잡아주고
		add(pnLogo); // 기본로고만 보이게

		PostUI post = new PostUI(f); // 포스팅 패널 객체 생성. 메인화면 메소드 호출하려고 FramePost f 넘겨줌
		post.setPreferredSize(new Dimension(540, 750)); // 면적 잡아주고
		pnBg.add(post);
		pnBg.repaint();
	}

	public void showMe() { // 세번째 화면(내 피드)
		remove(pnLogo);
		pnBg.removeAll(); // 기존 패널 컴포넌트 삭제

		scroll.setBounds(0, 210, 525, 640); // 로고 위치 때문에 스크롤 크기 줄여주기
		scroll.getVerticalScrollBar().setValue(0);
		add(pnLogo2); // 내아이디로고 보이게

		pnProfile = new ProfileUI(); // 프로필 패널
		pnProfile.setBounds(5, 70, 505, 130);
		add(pnProfile);

		myPost = new MyPostUI();
		if (myPost.height == 0) { // 댓글이 하나도 없으면, 화면에 안 뜬게 아니라 보여줄게 없다고 알림
			pnBg.add(new JLabel("hey you have no post2"));
		}
		myPost.setPreferredSize(new Dimension(540, myPost.height)); // 면적 잡아주고
		pnBg.add(myPost);
		pnBg.repaint();
		repaint();
	}

	public void startSetting() { // 로그인 성공하고 들어오면 실행되는 메소드
		pnBg.removeAll();

		add(btnHome);
		add(btnPost);
		add(btnMe);

		repaint();
		showHome();
	}

	public void showMyInfo() {
		remove(pnProfile);
		pnBg.removeAll();

		pnEdit = new MyInfoUpdateUI();
		pnBg.add(pnEdit);
		pnBg.repaint();
		repaint();
	}
}
