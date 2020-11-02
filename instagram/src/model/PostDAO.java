package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DBCon;

public class PostDAO {
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public void create(PostDTO dto) { // 게시글 생성
		try {
			String sql = "INSERT INTO post VALUES (null, ?, ?, ?, null, 0, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getpImg());
			ps.setString(2, dto.getpCon());
			ps.setString(3, dto.getpCDate());
			ps.setString(4, dto.getuID());
			ps.executeUpdate();
			ps.close();
			System.out.println("[Post] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] INSERT failed.");
		}
	}

	public ArrayList<PostDTO> read() { // 게시물 조회
		ArrayList<PostDTO> postList = new ArrayList<PostDTO>();

		try {
			String sql = "SELECT * FROM post ORDER BY pNo DESC "; // 내림차순으로 조회해서 최근 글부터 볼 수 있도록
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PostDTO dto = new PostDTO();

				int pNo = rs.getInt("pNo");
				String pImg = rs.getString("pImg");
				String pCon = rs.getString("pCon");
				int pHeart = rs.getInt("pHeart");
				String pCDate = rs.getString("pCDate");
				String pUDate = rs.getString("pUDate");
				String uID = rs.getString("uID");

				dto.setpNo(pNo);
				dto.setpImg(pImg);
				dto.setpCon(pCon);
				dto.setpHeart(pHeart);
				dto.setpCDate(pCDate);
				dto.setpUDate(pUDate);
				dto.setuID(uID);
				postList.add(dto);
			}
			ps.close();
			rs.close();
			System.out.println("[Post] SELECT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] SELECT failed.");
		}

		return postList;
	}

	public ArrayList<PostDTO> read(String uID) { // 내 게시물만 조회
		ArrayList<PostDTO> myPostList = new ArrayList<PostDTO>();

		try {
			String sql = "SELECT * FROM post WHERE uID='" + uID + "' ORDER BY pNo DESC ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PostDTO dto = new PostDTO(); // 하나씩 넣어야 하니까 while돌릴때마다 생성

				int pNo = rs.getInt("pNo");
				String pImg = rs.getString("pImg");
				String pCon = rs.getString("pCon");
				int pHeart = rs.getInt("pHeart");
				String pCDate = rs.getString("pCDate");
				String uID2 = rs.getString("uID");

				dto.setpNo(pNo);
				dto.setpImg(pImg);
				dto.setpCon(pCon);
				dto.setpHeart(pHeart);
				dto.setpCDate(pCDate);
				dto.setuID(uID2);
				myPostList.add(dto);
			}
			ps.close();
			rs.close();
			System.out.println("[Post] SELECT (uID) completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] SELECT (uID) failed.");
		}

		return myPostList;
	}
	
	public int readCount(String uID) { // 내 게시물 개수 조회
		int count = 0;

		try {
			String sql = "SELECT COUNT(*) FROM post WHERE uID='" + uID + "' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
			ps.close();
			rs.close();
			System.out.println("[HEART] SELECT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] SELECT failed.");
		}

		return count;
	}

	public void update(PostDTO dto) { // 게시물 수정
		try {
			String sql = "UPDATE post SET pCon=?, pUDate=? WHERE pNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getpCon());
			ps.setString(2, dto.getpUDate());
			ps.setInt(3, dto.getpNo());
			ps.executeUpdate();
			ps.close();
			System.out.println("[Post] UPDATE completed.");
		} catch (Exception e) {
			System.out.println("---[Post] UPDATE failed.");
		}
	}

	public int update(int pNo, int value) { // 게시물의 좋아요 수정(value는 1 혹은 -1로 세팅해놓음)
		int pHeart = 0;

		try {
			// 일단 기존의 좋아요 개수를 구하자
			String sql = "SELECT pHeart FROM post WHERE pNo=" + pNo + " ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pHeart = rs.getInt("pHeart");
			}

			// 그리고 기존의 좋아요에다 현재 결과 반영
			sql = "UPDATE post SET pHeart=? WHERE pNo=" + pNo + " ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pHeart + value);
			ps.executeUpdate();
			ps.close();
			rs.close();
			System.out.println("[Post] UPDATE pHeart completed.");
		} catch (Exception e) {
			System.out.println("---[Post] UPDATE pHeart failed.");
		}
		return (pHeart + value);
	}

	public void delete(PostDTO dto) {
		try {
			String sql = "DELETE FROM post WHERE pNo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, dto.getpNo());
			ps.executeUpdate();
			ps.close();
			System.out.println("[Post] DELETE completed.");
		} catch (Exception e) {
			System.out.println("---[Post] DELETE failed.");
		}
	}
}
