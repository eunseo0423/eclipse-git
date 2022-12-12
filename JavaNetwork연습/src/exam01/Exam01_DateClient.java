package exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam01_DateClient extends Application {

	// field
	TextArea textarea;
	Button connBtn;
	
	@Override
	public void start(Stage primaryStage) throws Exception { // 윈도우창
		
		// 화면구성을 해보아용
		// 일단 레이아웃부터 하나 만들어 보아요
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// component 생성
		textarea = new TextArea();
		root.setCenter(textarea);
		
		connBtn = new Button("Date 서버 접속");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// 접속 버튼을 클릭하면 하는 일을 여기에 작성
				// 서버와 접속하는 코드를 만들어 주면 돼요
				textarea.clear(); // textarea 안의 내용을 다 지움
				// 서버에 접속해요 = 소캣객체 생성을 시도
				// 서버에 접속하려면 IP 주소와 Port를 알아야함
				// 자신을 찾을 때 사용하면 IP :  127.0.0.1, localhost 둘중에 하나 입력하면됨
				
				try {
					Socket s = new Socket("127.0.0.1", 3000); // 네트워크를 통해서 나를 찾을 수도 있음 => rootback(정해져 있음), localhost
					System.out.println("서버에 접속성공");
					
					BufferedReader br = // 좋은 통로
							new BufferedReader(new InputStreamReader(s.getInputStream())); // 통로 만들음
					
					String msg = br.readLine(); // 통로로부터 데이터를 읽을거에요, 통로가 먼저 만들어지고 들어올때까지 대기
					
					System.out.println(msg);
					
					br.close();
					s.close();
					
					System.out.println("서버와 연결종료");
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 성공하면 소켓이 만들어짐
				
				
//				System.out.println("서버에 접속해요");
			}
		});
		
		
		FlowPane flowpane = new FlowPane(); // 아래쪽 영역에 붙는 layout
		// 안해도 되지만 여백좀 잡아서 보기 좋게 만들기 위해서 (4가지)
		flowpane.setPadding(new Insets(10, 10, 10, 10)); // 여백설정
		flowpane.setColumnHalignment(HPos.CENTER); // 가운데 정렬
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 버튼을 flowpane에 붙여요
		flowpane.getChildren().add(connBtn);
		
		// 이렇게 만든 flowpane을 borderlayout의 아래쪽에 붙여야 해요
		root.setBottom(flowpane);
		
		// 고정 (장면 만들고, 붙이고, 띄우고)
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
