package Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exam01_Keyboardinput {

	public static void main(String[] args) {
		
		System.out.println("키보드로 한줄을 입력하세요!");
		
		// 키보드로부터 입력을 받으려면 데이터 연결통로(Stream)이 있어야 해요
		
		// system.in이 제공되는데 그냥 InputStream class의 객체임. 사용하기 너무 불편
		// 문자 기반의 데이터를 받기를 원하니 Reader를 하나 만들어야해요.
//		new InputStreamReader(System.in); // 키보드를 통한 문자 기반의 연결통로 뚫음
		// 이렇게 Stream을 결합해서 조금 더 편한 문자 기반의 통로를 열었어요. 그럼에도 불가하고 불편
		// 조금 더 편한 문자 기반의 데이터 입력 연결 통로를 만들어 볼게용
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in)); // 결합
		
		try {
			String s = br.readLine(); // 한 줄을 읽어와
			System.out.println("입력받은 데이터는 : " + s);
		} catch (IOException e) {
		}
		
	}
}
