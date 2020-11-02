package model;

public class UserVO {
	private String uID;
	private String uPW;
	private String uPhone;
	private String uCDate;
	private String cPW; //변경할 비밀번호
	private String cPWC; //변경할 비밀번호 체크
	
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getcPW() {
		return cPW;
	}
	public String getcPWC() {
		return cPWC;
	}
	public void setcPWC(String cPWC) {
		this.cPWC = cPWC;
	}
	public void setcPW(String cPW) {
		this.cPW = cPW;
	}
	public String getuPW() {
		return uPW;
	}
	public void setuPW(String uPW) {
		this.uPW = uPW;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuCDate() {
		return uCDate;
	}
	public void setuCDate(String uCDate) {
		this.uCDate = uCDate;
	}

}
