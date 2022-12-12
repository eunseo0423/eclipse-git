package javafxexam;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {

	@Override
	public void start(Stage primarytStage) throws Exception {
		// 화면 구성하는 작업을 여기서 해요
		
		// 창 하나 버튼 하나
		Button btn = new Button(); // 버튼 만들음
		btn.setText("안녕!!"); // 버튼 위의 글자 (api reference 통해 사용 참고)
		// button의 이벤트 처리를 해야해요. 이벤트 = 사용자가하는 모든 행위
		// Java는 delegation model을 이용해요. -> Web 의 JavaScript도 이방식을 이용
		
		//button 에 action이라는 event를 처리할 수 있는 Listener객체를 붙여요
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) { // arg0로 전달
				// 이벤트가 발생하면 자동으로 호출되는군요!
				System.out.println("안녕하세요!");
				
			}
		});
		
		// Layout을 설정(컴포넌트가 붙는 방식을 결정하는 객체)
		StackPane root = new StackPane(); //판을 만들음
		
		root.getChildren().add(btn); 
		
		// Scene 객체를 생성
		Scene scene = new Scene(root, 300, 150); // 가로, 세로 / Stage가 창
		
		// Stage는 method의 인자로 기본적으로 제공이 됨
		
		primarytStage.setTitle("연습입니다");
		
		primarytStage.setScene(scene);
		
		primarytStage.show();
	}
	
	public static void main(String[] args) {
		launch();
		// GUI Thread가 실행되고 화면에 창이 떠요
	}
}
