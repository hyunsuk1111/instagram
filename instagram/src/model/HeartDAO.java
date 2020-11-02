package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBCon;

public class HeartDAO {
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public void create(HeartDTO dto) { // 좋아요 생성
		try {
			String sql = "INSERT INTO heart VALUES (null, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getuID());
			ps.setInt(2, dto.getpNo());
			ps.executeUpdate();
			ps.close();
			System.out.println("[HEART] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] INSERT failed.");
		}
	}

	public boolean read(int pNo, String uID) { // 좋아요 중복 적용 막기 위해 기존에 눌렀는지 조회(한 게시글마다 유저별로 좋아요 최대 1회 가능)
		boolean result = false;
		try {
			String sql = "SELECT * FROM heart WHERE pNo=" + pNo + " AND uID='" + uID + "' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}
			ps.close();
			rs.close();
			System.out.println("[HEART] SELECT (Heart) completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] SELECT (Heart) failed.");
		}

		return result;
	}

	public void delete(HeartDTO dto) { // 좋아요 삭제
		try {
			String sql = "DELETE FROM heart WHERE uID=? and pNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getuID());
			ps.setInt(2, dto.getpNo());
			ps.executeUpdate();
			ps.close();
			System.out.println("[HEART] DELETE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] DELETE failed.");
		}
	}
}
