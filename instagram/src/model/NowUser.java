package model;

public class NowUser {
	private static String loginID = "aaaa";
	
	public static void setloginID(String loginID) {
		NowUser.loginID = loginID;
	}
	public static String getloginID() {
		return loginID;
	}
}
