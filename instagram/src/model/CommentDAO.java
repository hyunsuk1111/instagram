package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCon;

public class CommentDAO {
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public void create(CommentDTO dto) { // 댓글 생성
		try {
			String sql = "INSERT INTO comment VALUES (null, ?, ?, null, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getcCon());
			ps.setString(2, dto.getcCDate());
			ps.setString(3, dto.getuID());
			ps.setInt(4, dto.getpNo());

			ps.executeUpdate();

			ps.close();
			System.out.println("[Comment] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Comment] INSERT failed.");
		}
	}

	public ArrayList<CommentDTO> read(String col, int no) { // 댓글 조회(DTO로 반환해서 화면에서 풀어씀)
		ArrayList<CommentDTO> commentList = new ArrayList<CommentDTO>();

		try {
			String sql = "SELECT * FROM comment WHERE " + col + "=" + no + " "; // cNo, pNo 둘 다 조회하려고
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CommentDTO dto = new CommentDTO();

				int cNo = rs.getInt("cNo");
				String cCon = rs.getString("cCon");
				String cCDate = rs.getString("cCDate");
				String cUDate = rs.getString("cUDate");
				String uID = rs.getString("uID");

				dto.setcNo(cNo);
				dto.setcCon(cCon);
				dto.setcCDate(cCDate);
				dto.setcUDate(cUDate);
				dto.setuID(uID);
				commentList.add(dto);
			}

			ps.close();
			rs.close();
			System.out.println("[Comment] SELECT completed.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("---[Comment] SELECT failed.");
		}

		return commentList;
	}

	public void update(CommentDTO dto) { // 댓글 수정(DTO로 반환해서 화면에서 풀어씀)
		try {
			String sql = "UPDATE comment SET cCon=?, cUDate=? WHERE cNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getcCon());
			ps.setString(2, dto.getcUDate());
			ps.setInt(3, dto.getcNo());
			ps.executeUpdate();
			ps.close();
			System.out.println("[Comment] UPDATE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Comment] UPDATE failed.");
		}
	}

	public void delete(CommentDTO dto) { // 댓글 삭제
		try {
			String sql = "DELETE FROM comment WHERE cNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getcNo());
			ps.executeUpdate();
			ps.close();
			System.out.println("[Comment] DELETE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Comment] DELETE failed.");
		}
	}
}
