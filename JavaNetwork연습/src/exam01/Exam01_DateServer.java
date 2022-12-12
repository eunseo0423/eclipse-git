package exam01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

public class Exam01_DateServer {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(3000);  // port 번호 3000 입력
			System.out.println("서버소켓이 생성되었어요 - 포트번호 3000");
			// 이 서버소켓을 통해 클라이언트의 접속을 기다려야함
			Socket s = server.accept(); // method이름이 accept. = 저 포트번호로 누군가 찾아올 때 까지 대기. 클라이언트가 접속할 때까지 대기
			System.out.println("클라이언트의 접속이 들어왔어요");
			
			// 클라이언트와 연결된 output stream(데이터를 내보내기 위한 stream)을
			// 사용하기 편한 PrintWriter로 만들어요
			PrintWriter out = new PrintWriter(s.getOutputStream()); // 조금 더 좋은 stream
//			s.getOutputStream() // 소켓에서 나가는 stream. 문자열을 보내기에 적합한 스트림이 아님 
			
			// 현재 시간을 보내줄거야
			DateFormat dateFormat = DateFormat.getInstance(); // 현재 날짜와 시간 구하는 법 
			String currentDate = dateFormat.format(new Date()); // import java.util.Date; . 내가 보내줄 데이터 구함
			
			out.println(currentDate); // 통로를 통해 client에 데이터를 보냄.
			// 관을 통해 가는 거라고 생각하는데 실제로는 아님. 데이터를 싫은 버퍼(마차)
			out.flush(); // 마차 출발시켜(데이터 감)
			
			// stream을 닫아요
			out.close();
			
			// socket 닫기
			s.close();
			
			// server socket 닫기
			server.close();
			
			System.out.println("서버프로그램 종료");
			
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
