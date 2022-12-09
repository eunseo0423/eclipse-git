package Sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Exam02_ObjectStream {

	public static void main(String[] args) {
		
		// 먼저 스트림을 통해 내보낼 HashMap을 간단하게 만들어보아요
		HashMap<String, String> map = 
				new HashMap<String, String>();
		
		
		map.put("1", "홍길동");
		map.put("2", "신사임당");
		map.put("3", "강감찬");
		
		// 실제 파일을 생성하려면 당연히 자바쪽에서 File 객체를 만들어야해요
		File file = new File("readme.txt");
		
		try {
			FileOutputStream fis = new FileOutputStream(file); // 데이터를 내보내는 통로
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			
			oos.writeObject(map); // 이 통로를 통해서 HashMap을 내보냄
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
