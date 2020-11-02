package model;

public class PostDTO {
	private int pNo;
	private String pImg;
	private String pCon;
	private String pCDate;
	private String pUDate;
	private int pHeart;
	private String uID;
	
	public PostDTO() {
		super();
	}
	public PostDTO(int pNo, String pImg, String pCon, String pCDate, String pUDate, int pHeart, String uID) {
		super();
		this.pNo = pNo;
		this.pImg = pImg;
		this.pCon = pCon;
		this.pCDate = pCDate;
		this.pUDate = pUDate;
		this.pHeart = pHeart;
		this.uID = uID;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getpImg() {
		return pImg;
	}
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}
	public String getpCon() {
		return pCon;
	}
	public void setpCon(String pCon) {
		this.pCon = pCon;
	}
	public String getpCDate() {
		return pCDate;
	}
	public void setpCDate(String pCDate) {
		this.pCDate = pCDate;
	}
	public String getpUDate() {
		return pUDate;
	}
	public void setpUDate(String pUDate) {
		this.pUDate = pUDate;
	}
	public int getpHeart() {
		return pHeart;
	}
	public void setpHeart(int pHeart) {
		this.pHeart = pHeart;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	@Override
	public String toString() {
		return pNo + "," + pImg + "," + pCon + "," + pCDate + "," + pUDate
				+ "," + pHeart + "," + uID;
	}
}
