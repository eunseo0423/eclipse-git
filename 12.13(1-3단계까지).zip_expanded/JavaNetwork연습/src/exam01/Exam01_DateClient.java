package exam01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	public void start(Stage primaryStage) throws Exception {
		// 화면구성을 해 보아요!
		// 일단 레이아웃부터 하나 만들어 보아요!
		BorderPane root = new BorderPane();
		root.setPrefSize(700,500);
		
		// component생성
		textarea = new TextArea();
		root.setCenter(textarea);
		
		connBtn = new Button("Date 서버 접속");
		connBtn.setPrefSize(150,40);
		connBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// 접속 버튼을 클릭하면 하는 일을 여기에 작성
				// 서버와 접속하는 코드를 만들어 주면 되요!
				textarea.clear(); // textarea안의 내용을 싹 다 지워요!
				// 서버에 접속해요! => Socket객체 생성을 시도.
				// 서버에 접속하려면 IP와 port를 알아야 해요!
				// 자신을 찾을때 사용하는 IP : 127.0.0.1 , localhost 
				try {
					Socket s = new Socket("127.0.0.1",3000);
					System.out.println("서버에 접속 성공!");
					BufferedReader br = 
							new BufferedReader(
									new InputStreamReader(s.getInputStream()));
					 
					String msg = br.readLine();
					
					System.out.println(msg);
					
					br.close();
					s.close();
					
					System.out.println("서버와 연결 종료!");
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		FlowPane flowpane = new FlowPane();  // 아래쪽 영역에 붙는 layout
		// 여백좀 잡아서 그나마 좀 보기 좋게 만들어보아요!
		flowpane.setPadding(new Insets(10, 10, 10, 10));
		flowpane.setColumnHalignment(HPos.CENTER);   // 가운데 정렬로 붙여요!
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 버튼을 flowpane에 붙여요
		flowpane.getChildren().add(connBtn);
		
		// 이렇게 만든 flowpane(판때기)를 borderlayout의 아래쪽에 붙여야 해요!
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
