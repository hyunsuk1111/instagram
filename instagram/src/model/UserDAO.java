package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ctrl.MainFrame;
import db.DBCon;
import ui.LogoUI;
import ui.SaveUserInfo;

public class UserDAO {
	// DB 연결 static
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	// 회원가입
	public void create(UserVO vo) throws Exception {
		// sql 문 작성
		String sql = "insert into user values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		// UserVO vo 객체를 전달받아 각 데이터들(uID,uPW,uPhone,uCDate)을 sql문에 대입
		ps.setString(1, vo.getuID());
		ps.setString(2, vo.getuPW());
		ps.setString(3, vo.getuPhone());
		ps.setString(4, vo.getuCDate());
		ps.executeUpdate(); // 업데이트
	}

	// 중복체크
	public boolean CheckID(String uID) throws Exception {
		boolean result = false; // return을 위한 논리 변수 생성
		// sql 문 작성
		String sql = "select * from user where uID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uID); // 전달받은 uID sql 문에 대입
		// query문 전송
		ResultSet rs = ps.executeQuery();
		// id가 존재하면
		if (rs.next()) {
			result = true; // 논리 값 false > true
		}
		return result; // 논리값 반환
	}

	// 로그인
	public void read(UserVO vo) throws Exception {
		// sql 문 작성
		String sql = "select * from user where uID = ? and uPW = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getuID()); // UserVO vo 객체를 전달받아 각 데이터들(uID,uPW)을 sql문에 대입
		ps.setString(2, vo.getuPW());
		// query문 전송
		ResultSet rs = ps.executeQuery();

		// id 존재 시(로그인 성공)
		if (rs.next()) {
			// 로그인정보저장
			String uID = rs.getString("uID"); // DB에 해당되는 uID,uPhone 값 저장
			SaveUserInfo.setloginID(uID); // 로그인 정보 SaveUserInfo 클래스에 전달

			// 화면전환
			MainFrame.f.pnLogo2 = new LogoUI(SaveUserInfo.getloginID());
			MainFrame.f.startSetting();
		} else { // id 존재하지 않는다면(로그인 실패)
			JOptionPane.showMessageDialog(null, "로그인 실패");
		}
	}

	// 회원탈퇴
	public void delete(String uID, String uPW) throws Exception {
		// sql 문 작성
		String sql1 = "select * from user where uID = ? and uPW = ?";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, uID); // UserVO vo 에 전달 받은 uID,uPW sql 문에 대입
		ps1.setString(2, uPW);
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) { // 아이디 존재하면
			int result2 = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?"); // 예 - 0 / 아니오 - 1 반환
			if (result2 == 0) { // '예' 버튼 클릭 시
				String sql2 = "delete from user where uid = ?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, uID); // UserVO vo 에 전달 받은 uID sql 문에 대입
				ps2.executeUpdate(); // 업데이트
				JOptionPane.showMessageDialog(null, "삭제완료");
			} else { // '취소' 버튼 클릭 시
				JOptionPane.showMessageDialog(null, "삭제취소");
			}
		} else { // 비밀번호 불일치 하면
			JOptionPane.showMessageDialog(null, "비밀번호 불일치");

		}
	}

	public String getUserPhone(String uID) throws Exception {
		String uPhone = null;
		String sql = "select * from user where uID = '" + uID + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			uPhone = rs.getString("uPhone");
		}

		return uPhone; // 현재 로그인한 ID의 전화번호 반환
	}

	public int update(UserVO vo) throws Exception {
		// sql 문 작성
		String sql1 = "select * from user where uID = ? and uPW =?";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, vo.getuID()); // UserVO vo 에 전달 받은 uID,uPW sql 문에 대입
		ps1.setString(2, vo.getuPW());
		ResultSet rs = ps1.executeQuery(); // query 문 전송
		int result = 0;

		if (!(rs.next())) { // id 와 비밀번호 불일치 시
			JOptionPane.showMessageDialog(null, "비밀번호가 올바르지 않습니다.");
		} else { // id와 비밀번호가 일치하다면
			// 변경할 비번과 변경할 비번체크가 일치하지 않을경우
			if (!(vo.getcPW().equals(vo.getcPWC()))) {
				JOptionPane.showMessageDialog(null, "변경할 비밀번호가 올바르지 않습니다.");
			} else { // 모두 만족한다면 회원정보 수정
				result = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", null, JOptionPane.YES_NO_OPTION);
				if (result == 0) { // 0 이면 예, 1 아니오
					if ((vo.getcPWC().equals("") && vo.getcPW().equals(""))) {
						String sql2 = "update user set uPhone = ? where uid = ?"; //전화번호만 수정 시 
						// UserVO vo 에 전달 받은 uID,uPW,uPhone sql 문에 대입
						PreparedStatement ps2 = con.prepareStatement(sql2);
						ps2.setString(1, vo.getuPhone());
						ps2.setString(2, vo.getuID());
						ps2.executeUpdate(); // 업데이트
						JOptionPane.showMessageDialog(null, "회원정보 수정 완료");
					} else {
						String sql2 = "update user set uPW = ?, uPhone = ? where uid = ?"; //전화번호와 비밀번호 수정시
						// UserVO vo 에 전달 받은 uID,uPW,uPhone sql 문에 대입
						PreparedStatement ps2 = con.prepareStatement(sql2);
						ps2.setString(1, vo.getcPW());
						ps2.setString(2, vo.getuPhone());
						ps2.setString(3, vo.getuID());
						ps2.executeUpdate(); // 업데이트
						JOptionPane.showMessageDialog(null, "회원정보 수정 완료");
					}

				} else { // 아니오 버튼 클릭 시
					JOptionPane.showMessageDialog(null, "회원정보 수정 취소");
				}
			}
		}
		return result; // result2 값 반환
	}

	// 유저 정보 파일로 저장
	public ArrayList<UserVO> getUser() throws Exception {
		// 리스트생성
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		// sql 문 작성
		String sql = "select * from user";
		PreparedStatement ps1 = con.prepareStatement(sql);
		ResultSet rs = ps1.executeQuery(); // query 문 전송
		while (rs.next()) { // user 테이블에 데이터가 존재하면
			UserVO vo = new UserVO(); // UserVO vo 객체 생성
			// 테이블 항목에 해당하는값 각 변수에 저장
			String uID = rs.getString("uID");
			String uPW = rs.getString("uPW");
			String uPhone = rs.getString("uPhone");
			String uCDate = rs.getString("uCDate");
			// 가방에 넣기
			vo.setuID(uID);
			vo.setuPW(uPW);
			vo.setuPhone(uPhone);
			vo.setuCDate(uCDate);
			// 리스트에 담기
			list.add(vo);
		}
		return list; // 리스트 반환
	}
}
