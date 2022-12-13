package exam02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam02_EchoClient extends Application{

	TextArea  textarea; 
	Button connBtn;
	TextField textField; // 한줄짜리 입력창
	TextField idField; // 아이디 입력창
	
	Socket s;
	PrintWriter pr; // 출력
	BufferedReader br; // 입력
	
	private void printMsg(String msg) {
		Platform.runLater(() -> { 
			textarea.appendText(msg + "\n");
		}); 
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500); // 크기 조절
		
		textarea = new TextArea();
		root.setCenter(textarea);// 중앙에 붙이기
		
		connBtn = new Button("서버접속!");
		connBtn.setPrefSize(150,40);// 사이즈 정해주기

		connBtn.setOnAction(e -> { // 눌러서 이벤트가 발생하면 수행 (위의 코드 축약해서 사용)
			try {
				s = new Socket("127.0.0.1", 5000); // Socket을 만든다
				printMsg("서버에 연결이 성공했습니다.");
				textField.setDisable(false); // 접속에 성공 후 다시 입력 칸 사용가능하게!
				pr = new PrintWriter(s.getOutputStream()); // socket을 통해 출력 stream을 열어줌
				br = new BufferedReader(new InputStreamReader(s.getInputStream())); // 입력 stream 만들어줌
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}); 
		
		// 아이디 
		idField = new TextField();
		idField.setPrefSize(200, 40);
		
		
		textField = new TextField();
		textField.setPrefSize(200, 40);
		textField.setDisable(true); // 처음에 입력 칸 사용할 수 없도록 세팅할까요? true(네)
		textField.setOnAction(e -> { // textField에서 엔터가 입력되면 코드 수행
			
			String msg = textField.getText(); // getText는 입력상자의 msg 가져오는 것 (api에서 확인 가능)
			String id = idField.getText();
			
			pr.println(id + "> " + msg); // 짐마차, 문자열 연결
			pr.flush(); // 서버로 보내주기
			
			
			// server한테 보낸 것 다시 받기
			// server가 break해도 기다리고 있음 => readLine이 무한대기상태
			
			if(!msg.equals("/exit")) {
				try {
					printMsg(br.readLine());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				printMsg("클라이언트 서버와 연결이 종료되었어요"); // 실제로는 종료되지 않았지만 서버가 종료되었으니까 메세지 올려주기
			}
			
			textField.clear(); // 엔터치면 비워주기
			
			
		});
		
		FlowPane flowPane = new FlowPane(); // 붙일 판 만들기
		flowPane.setPadding(new Insets(10, 10, 10, 10)); // 여백 잡기, Insets 는 여백 객체
		flowPane.setColumnHalignment(HPos.CENTER); // 정렬
		flowPane.setHgap(10);
		
		flowPane.getChildren().add(connBtn); // 버튼 붙이기
		flowPane.getChildren().add(idField); // 아이디칸 붙이기
		flowPane.getChildren().add(textField); // 텍스트칸 붙이기
		
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Echo Client Program");
		
		primaryStage.show(); // 창을 띄우세요
	}
		
	
	public static void main(String[] args) {
		launch();
	}

}
