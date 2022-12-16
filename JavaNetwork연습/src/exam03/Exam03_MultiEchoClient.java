package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

public class Exam03_MultiEchoClient extends Application {
	
	TextArea textArea;
	// 6. 버튼,입력상자 만들어주기
	TextField ipTextField;
	Button connBtn;
	TextField idTextField;
	TextField chatTextField;
	// 13-1. 클라이언트쪽 socket
	Socket socket;
	// 14. 데이터를 주고받는 통로
	PrintWriter pr;
	BufferedReader br;
	
	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		// 1. Border Pane 생성
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. TextArea 
		textArea = new TextArea();
		root.setCenter(textArea);
		
		// 4. 윗판자만들기
		FlowPane upFlowPane = new FlowPane();
		upFlowPane.setPadding(new Insets(10, 10, 10, 10)); // padding (여백설정)
		upFlowPane.setColumnHalignment(HPos.CENTER); // 정렬
		upFlowPane.setPrefSize(700, 40); // 크기
		upFlowPane.setHgap(10); 
		
		// 7. ipTextField
		ipTextField = new TextField();
		ipTextField.setPrefSize(200, 40);
		
		// 8. button
		connBtn = new Button("서버에 접속!");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(e -> {
			// 13. 서버와 접속 진행!!
			// 특정 IP와 Port 번호 이용해서 socket 객체 생성을 시도
			try {
				// 13-2. 만약 성공하면 서버와 연결된 socket객체를 하나 얻어요!
				socket = new Socket(ipTextField.getText(), 7777); // 서버쪽 port 번호가 7777
				
				// 14. 통로뚫기 (PrintWriter,BufferedReader는 거의 이렇게 쓰니까 알아두기)
				pr = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				// 15. 서버쪽 가서 비슷한 작업 해주기
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}); 
		
		// 9. upflowPane에 붙여주기
		upFlowPane.getChildren().add(ipTextField);
		upFlowPane.getChildren().add(connBtn);
		
		// 4-1. 아랫판자만들기
		FlowPane bottomFlowPane = new FlowPane();
		bottomFlowPane.setPadding(new Insets(10, 10, 10, 10)); // padding (여백설정)
		bottomFlowPane.setColumnHalignment(HPos.CENTER); // 정렬
		bottomFlowPane.setPrefSize(700, 40); // 크기
		bottomFlowPane.setHgap(10); 
		
		// 10. idTextField
		idTextField = new TextField();
		idTextField.setPrefSize(150, 40);
		
		// 11. chatTextField
		chatTextField = new TextField();
		chatTextField.setPrefSize(300, 40);
		
		// 16. (1) 채팅 입력 데이터 보내기
		chatTextField.setOnAction(e -> {
			// 채팅 입력창에서 엔터를 치면 액션 이벤트가 발생해서 이 코드가 실행됨
			String id = idTextField.getText(); // id 가져옴
			String msg = chatTextField.getText(); // msg 가져옴
			
			pr.println(id + "> " + msg); // 꼭 println(), 짐마차에 짐을 실음
			pr.flush(); // 데이터 보내기
			
			// 17. 서버쪽에 (2) 데이터 받는거 만들어주기
			// 18. (4)서버가 보낸 데이터 받기
			try {
				String receive = br.readLine();
				printMsg(receive);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		// 12. bottomflowPane에 붙여주기
		bottomFlowPane.getChildren().add(idTextField);
		bottomFlowPane.getChildren().add(chatTextField);
		
		// 5. 판자 붙이기
		root.setTop(upFlowPane);
		root.setBottom(bottomFlowPane);
	
		
		// 3. Scene
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch();
	}
}
