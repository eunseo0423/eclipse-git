package project.jdbc.view;

//import java.awt.TextField;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import project.jdbc.vo.BookVO;


public class LibrarySearchMVC extends Application {

	Stage window;
	Scene loginScene, joinScene, searchScene;
	
	TableView<BookVO> tableView;	
	TextField textField;
	String searchKeyword;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		// 1. Layout 설정
		
		// 로그인 창
		AnchorPane anchorpane = new AnchorPane();
		
		Label idLabel = new Label("ID");
		AnchorPane.setTopAnchor(idLabel, 42.0);
		AnchorPane.setLeftAnchor(idLabel, 52.0);
		AnchorPane.setRightAnchor(idLabel, 80.0);

		Label pwLabel = new Label("PW");
		AnchorPane.setTopAnchor(pwLabel, 75.0);
		AnchorPane.setLeftAnchor(pwLabel, 52.0);
		AnchorPane.setRightAnchor(pwLabel, 80.0);
		
		TextField idTextfield = new TextField();
		AnchorPane.setTopAnchor(idTextfield, 42.0);
		AnchorPane.setLeftAnchor(idTextfield, 90.0);
		
		TextField pwTextfield = new TextField();
		AnchorPane.setTopAnchor(pwTextfield, 75.0);
		AnchorPane.setLeftAnchor(pwTextfield, 90.0);

		Button loginBtn = new Button("로그인");
		AnchorPane.setTopAnchor(loginBtn, 110.0);
		AnchorPane.setLeftAnchor(loginBtn, 100.0);
		loginBtn.setOnAction(e -> window.setScene(searchScene));
		
		Button joinBtn = new Button("회원가입"); 
		AnchorPane.setTopAnchor(joinBtn, 110.0);
		AnchorPane.setLeftAnchor(joinBtn, 180.0);
		joinBtn.setOnAction(e -> window.setScene(joinScene));

		
	    anchorpane.getChildren().addAll(idLabel, pwLabel, idTextfield,
	    		pwTextfield, loginBtn, joinBtn);

					
		loginScene = new Scene(anchorpane, 320, 180);
		window.setScene(loginScene);
		window.setTitle("로그인");
		window.show();
		
		// 회원가입 창
		
		AnchorPane anchorpane2 = new AnchorPane();
		
		Label nameLabel = new Label("이름");
		AnchorPane.setTopAnchor(nameLabel, 42.0);
		AnchorPane.setLeftAnchor(nameLabel, 52.0);
		AnchorPane.setRightAnchor(nameLabel, 80.0);
		
		Label ageLabel = new Label("나이");
		AnchorPane.setTopAnchor(ageLabel, 75.0);
		AnchorPane.setLeftAnchor(ageLabel, 52.0);
		AnchorPane.setRightAnchor(ageLabel, 80.0);

		Label inputIdLabel = new Label("ID");
		AnchorPane.setTopAnchor(inputIdLabel, 108.0);
		AnchorPane.setLeftAnchor(inputIdLabel, 52.0);
		AnchorPane.setRightAnchor(inputIdLabel, 80.0);

		Label inputPwLabel = new Label("PW");
		AnchorPane.setTopAnchor(inputPwLabel, 141.0);
		AnchorPane.setLeftAnchor(inputPwLabel, 52.0);
		AnchorPane.setRightAnchor(inputPwLabel, 80.0);
		
		TextField nameTextfield = new TextField();
		AnchorPane.setTopAnchor(nameTextfield, 42.0);
		AnchorPane.setLeftAnchor(nameTextfield, 90.0);
		
		TextField ageTextfield = new TextField();
		AnchorPane.setTopAnchor(ageTextfield, 75.0);
		AnchorPane.setLeftAnchor(ageTextfield, 90.0);
		
		TextField inputIdTextfield = new TextField();
		AnchorPane.setTopAnchor(inputIdTextfield, 108.0);
		AnchorPane.setLeftAnchor(inputIdTextfield, 90.0);
		
		TextField inputPwTextfield = new TextField();
		AnchorPane.setTopAnchor(inputPwTextfield, 141.0);
		AnchorPane.setLeftAnchor(inputPwTextfield, 90.0);
		
		Button joinMemberBtn = new Button("회원가입"); 
		AnchorPane.setTopAnchor(joinMemberBtn, 176.0);
		AnchorPane.setLeftAnchor(joinMemberBtn, 140.0);
		joinMemberBtn.setOnAction(e -> window.setScene(loginScene));

		
	    anchorpane2.getChildren().addAll(nameLabel, ageLabel, inputIdLabel, 
	    		inputPwLabel, nameTextfield, ageTextfield, 
	    		inputIdTextfield, inputPwTextfield, joinMemberBtn);

					
		joinScene = new Scene(anchorpane2, 320, 250);
		window.setScene(loginScene);
		window.setTitle("회원가입");
		window.show();
		
		// 도서검색 및 대여 창 (HOME)
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 검색
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		textField.setOnAction(e -> {
			// 이벤트 처리
			// Controller를 이용해서 Service에 로직 실행 요청을 해야 해요!
			// 먼저 Controller객체를 생성할꺼예요!
			BookSearchByKeywordController controller = 
					new BookSearchByKeywordController();
			
			ObservableList<BookVO> list = 
					controller.getResult(textField.getText());
			
			tableView.setItems(list);
			
			searchKeyword = textField.getText();
			textField.clear();
		});
		
		// 대여
		
		// MY
		
		// 도서정보관리
		
		// 회원정보관리
		
		
	}

	public static void main(String[] args) {
		launch();
	}
}
