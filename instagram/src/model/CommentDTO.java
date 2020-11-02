package model;

public class CommentDTO {
	private int cNo;
	private String cCon;
	private String cCDate;
	private String cUDate;
	private String uID;
	private int pNo;
	
	public CommentDTO() {
		super();
	}
	public CommentDTO(int cNo, String cCon, String cCDate, String cUDate, String uID, int pNo) {
		super();
		this.cNo = cNo;
		this.cCon = cCon;
		this.cCDate = cCDate;
		this.cUDate = cUDate;
		this.uID = uID;
		this.pNo = pNo;
	}
	public CommentDTO(int cNo, String cCon, String cCDate, String uID, int pNo) {
		super();
		this.cNo = cNo;
		this.cCon = cCon;
		this.cCDate = cCDate;
		this.uID = uID;
		this.pNo = pNo;
	}

	public int getcNo() {
		return cNo;
	}
	public void setcNo(int cNo) {
		this.cNo = cNo;
	}
	public String getcCon() {
		return cCon;
	}
	public void setcCon(String cCon) {
		this.cCon = cCon;
	}
	public String getcCDate() {
		return cCDate;
	}
	public void setcCDate(String cCDate) {
		this.cCDate = cCDate;
	}
	public String getcUDate() {
		return cUDate;
	}
	public void setcUDate(String cUDate) {
		this.cUDate = cUDate;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	
	@Override
	public String toString() {
		return cNo + "," + cCon + "," + cCDate + "," + cUDate + "," + uID
				+ "," + pNo;
	}
}
