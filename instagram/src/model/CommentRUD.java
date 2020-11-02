package model;

public class CommentRUD {
	CommentDTO dto = new CommentDTO();
	CommentDAO dao = new CommentDAO();
	
	public void createComment(String cCon, String cCDate, String uID, int pNo) { // 댓글 생성 컨트롤러
		try {
			dto.setcCon(cCon);
			dto.setcCDate(cCDate);
			dto.setuID(uID);
			dto.setpNo(Integer.valueOf(pNo));
			dao.create(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Comment] execute CREATE failed!!");
		}
	}
	
	public void updatePost(int cNo, String cCon, String cUDate) { // 댓글 수정 컨트롤러
		try {
			dto.setcCon(cCon);
			dto.setcUDate(cUDate);
			dto.setcNo(cNo);
			dao.update(dto);
		} catch (Exception e) {
			System.out.println("[Comment] execute UPDATE failed!!");
		}
	}

	public void deletePost(int cNo) { // 댓글 삭제 컨트롤러
		try {
			dto.setcNo(cNo);
			dao.delete(dto);
		} catch (Exception e) {
			System.out.println("[Comment] execute DELETE failed!!");
		}
	}
}
