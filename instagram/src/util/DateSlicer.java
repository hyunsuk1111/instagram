package util;

// DB에 저장된 날짜 쪼개서 보기좋게 변환
public class DateSlicer {
	
	public static String slice(String date) {
		// 20200505101545 -> 2020.05.05 10:15  // 초 없앰
		String year = date.substring(0, 4) + ".";
		String month = date.substring(4, 6) + ".";
		String day = date.substring(6, 8) + " ";
		String hour = date.substring(8, 10) + ":";
		String min = date.substring(10, 12);

		return year + month + day + hour + min;
	}
	
	public static String slice2(String date) {
		// 20200505101545 -> 2020.05.05 10:15:45  // 초 있게
		String year = date.substring(0, 4) + ".";
		String month = date.substring(4, 6) + ".";
		String day = date.substring(6, 8) + " ";
		String hour = date.substring(8, 10) + ":";
		String min = date.substring(10, 12) + ":";
		String sec = date.substring(12, 14);

		return year + month + day + hour + min + sec;
	}
}
