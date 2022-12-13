package exam02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam02_EchoServer extends Application { // Application은 추상class

	TextArea  textarea; // field로 잡아서 우리 class 내에서 자유롭게 사용하기 좋음
	Button startBtn;
	Button stopBtn;
	ServerSocket server;
	Socket s; // field로 잡아야 쓰기 편함
	PrintWriter pr; // 출력
	BufferedReader br; // 입력
	
	// 자주 쓰기 때문에 우리 class 내부에서 사용하기 위해 private method로 만들어주기
	private void printMsg(String msg) {
		Platform.runLater(() -> { 
			textarea.appendText(msg + "\n");
		}); 
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception { // primaryStage는 창 객체
		// 창의 화면 구성을 하게 돼요
		// Layout
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500); // 크기 조절
		
		textarea = new TextArea();
		root.setCenter(textarea);// 중앙에 붙이기
		
		startBtn = new Button("서버시작!");
		startBtn.setPrefSize(150,40);// 사이즈 정해주기
		// 버튼을 클릭했으르때(클릭은 ActionEvent) 이벤트 처리가 나와요
		// 이벤트 처리는 Listener 객체(Handler객체)가 담당 => delegation model
//		startBtn.setOnAction(new EventHandler<ActionEvent>() { // 버튼에 handler객체 붙여주기
//			
//			@Override
//			public void handle(ActionEvent e) { // 버튼을 눌렀을때 코드 수행
//				// TODO Auto-generated method stub
//				
//			}
//		}); 
		startBtn.setOnAction(e -> { // 눌러서 이벤트가 발생하면 수행 (위의 코드 축약해서 사용)
			// 버튼을 누르면 textarea에 append 
//			textarea.appendText("서버가 시작되었어요!" + "\n");// \n => 줄바꿈
//			for(long k=0; k<90000000000L; k++); // busy waiting
			// blocking method! 따라서 실행되는 동안 수행이 잠시 중단됨
			// 이렇게 하면 안됨
			// 순차처리를 안하기 위해서 Thread 사용
//			Platform.runLater(new Runnable() { // static method
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					
//				}
//				
//			}); 
			
//			Platform.runLater(() -> { 
//				textarea.appendText("서버가 시작되었어요!" + "\n");
//			}); 
			
			printMsg("서버가 시작되었어요!");
			try {
				server = new ServerSocket(5000);
				printMsg("클라이언트 접속대기중!");
//				server.accept(); // 먼저 찍고 accept 해서 기다려야함 => blocking 됨
				
				(new Thread(() -> {
					try {
						s = server.accept(); // client와 연결된 socket 만듦
						printMsg("클라이언트 접속성공!");
						// stream 
						pr = new PrintWriter(s.getOutputStream()); // socket을 통해 출력 stream을 열어줌
						br = new BufferedReader(new InputStreamReader(s.getInputStream())); // 입력 stream 만들어줌
						
						while(true) { // 보내고 client가 무엇을 보내줄 지 다시 기다리고 반복
							//client가 보냈으니 받기
							String msg = br.readLine(); // 단발성
							printMsg("클라이언트의 메시지 : " + msg);
							
							if(msg.equals("/exit")) {
								break; // 자기와 인접한 가장 가까운 반복 루프를 나가는 것이 break
							}
							//client에 다시 보내주기 
							pr.println(msg);
							pr.flush();
						} // break -> 여기로 나옴
						
						printMsg("클라이언트 종료! & 서버 Process 종료");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				})).start();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}); 
		
		stopBtn = new Button("서버중지!!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(null);
		
		FlowPane flowPane = new FlowPane(); // 붙일 판 만들기
		flowPane.setPadding(new Insets(10, 10, 10, 10)); // 여백 잡기, Insets 는 여백 객체
		flowPane.setColumnHalignment(HPos.CENTER); // 정렬
		flowPane.setHgap(10);
		
		flowPane.getChildren().add(startBtn); // 버튼 붙이기
		flowPane.getChildren().add(stopBtn);
		
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Echo Server Program");
		
		primaryStage.show(); // 창을 띄우세요
	}
	
	public static void main(String[] args) {
		launch(); //static method, GUI Thread가 생성돼서 우리 창이 실행돼요.
 	}

}
