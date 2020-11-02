package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.UserVO;

//유저정보 파일로 저장
public class FileSave {

	// ArrayList를 인자로 받음
	public void save(ArrayList<UserVO> list) {
		try {
			// FileWriter 객체 생성 및 Userdata.txt로 저장 선언
			FileWriter w = new FileWriter("Userdata.txt");
			// 전달받은 Size 만큼 반복하면서 데이터(uID,uPhone,
			for (int j = 0; j < list.size(); j++) {
				UserVO data = list.get(j); // 가방에 리스트 저장
				w.append("아이디 : " + data.getuID() + "\n");
				w.append("전화번호 : " + data.getuPhone() + "\n");
				w.append("회원가입시간 : " + DateSlicer.slice2(data.getuCDate()) + "\n");
				w.append("\n");
			}
			w.close(); // 파일 close
			JOptionPane.showMessageDialog(null, "저장완료");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
