package model;

public class AllPostRUD {
	PostDAO dao = new PostDAO();
	PostDTO dto = new PostDTO();

	public void updatePost(int pNo, String pCon, String pUDate) { // 게시글 수정 컨트롤러
		try {
			dto.setpCon(pCon);
			dto.setpUDate(pUDate);
			dto.setpNo(pNo);

			dao.update(dto);
		} catch (Exception e) {
			System.out.println("[Post] execute UPDATE failed!!");
		}
	}

	public void deletePost(int pNo) { // 게시글 삭제 컨트롤러
		try {
			dto.setpNo(pNo);

			dao.delete(dto);
		} catch (Exception e) {
			System.out.println("[Post] execute DELETE failed!!");
		}
	}
}
