package model;

public class HeartDTO {
	private int hNo;
	private int pNo;
	private String uID;

	public HeartDTO() {
		super();
	}

	public HeartDTO(int hNo, int pNo, String uID) {
		super();
		this.hNo = hNo;
		this.pNo = pNo;
		this.uID = uID;
	}

	public int gethNo() {
		return hNo;
	}

	public void sethNo(int hNo) {
		this.hNo = hNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	@Override
	public String toString() {
		return hNo + "," + pNo + "," + uID;
	}
}
