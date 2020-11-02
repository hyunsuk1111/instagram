package ui;

public class SaveUserInfo { // 로그인 시 유저 정보 저장
	private static String loginID;

	public static void setloginID(String loginID) {
		SaveUserInfo.loginID = loginID;
	}

	public static String getloginID() {
		return loginID;
	}
}
