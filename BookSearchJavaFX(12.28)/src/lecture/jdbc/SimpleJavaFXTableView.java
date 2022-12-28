package lecture.jdbc;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class SimpleJavaFXTableView extends Application {

//	TextArea textArea;
	// TextArea 영역에 TableView 붙일 것
	// TableView안에 데이터를 표현할 때 VO를 가져다가 한 줄 씩 표현하게 돼요
	// 그 때 어떤 VO를 사용하는지에 대한 class이름을 generic으로 지정해야 해요
	TableView<BookVO> tableView; // 어떤 VO 사용할 것인지 지정해준 것
	
	TextField textField;
	
	@Override
	public void start(Stage primaryStage) throws Exception { // 실제 화면에 띄우는 창
		
		// 1. layout부터 설정해야 해요
		// BorderPane을 이용할 거에요 (동서남북중앙)
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. BorderPane 아래쪽에 붙일 판자(FlowPane)를 하나 생성, 속성 설정
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10)); // 여백
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 3. 각각의 component를 생성해서 붙여요
		textField = new TextField();
		textField.setPrefSize(250, 40);
		flowpane.getChildren().add(textField);
		
		// 컬럼 객체를 생성해요
		// TableColumn<이 컬럼에 나오는 데이터를 어떤 VO에서 떙기는 지 클래스를 명시, 해당 컬럼에 대한 datatype(VO에서 값을 가져올 때 사용하는 데이터 타입) >
		TableColumn<BookVO, String> isbnColumn =
				new TableColumn<>("ISBN"); // 화면에 보여지는 column의 이름
		isbnColumn.setMinWidth(150); // 컬럼의 최소 가로 픽셀길이
		// 해당 컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn =
				new TableColumn<>("TITLE"); 
		titleColumn.setMinWidth(150); 
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<BookVO, String> authorColumn =
				new TableColumn<>("AUTHOR"); 
		authorColumn.setMinWidth(150); 
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, Integer> priceColumn =
				new TableColumn<>("PRICE"); 
		priceColumn.setMinWidth(150); 
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		// TableView에 표현할 데이터를 만들어 보아요!
		// 책들을 만들거에요
		// TableView에 데이터를 밀어넣을때는 ArrayList를 쓰지않고
		// ArrayList와 유사한 리스트를 사용(Table에서 사용하는 ArrayList라 생각해면 됨. 사용하는 법 동일)
		ObservableList<BookVO> list = FXCollections.observableArrayList();
		
//		ArrayList<BookVO> list = new ArrayList<BookVO>();
		list.add(new BookVO("123", "java 30일 완성", "홍길동", 2000));
		list.add(new BookVO("456", "java 이제 그만", "신사임당", 5000));
		list.add(new BookVO("789", "어려워요", "강감찬", 8000));
		
		
		
//		textArea = new TextArea();
		tableView = new TableView<BookVO>();
		
		// tableView에 위에서 만들어진 column 객제 붙이기
		tableView.getColumns().addAll(isbnColumn, titleColumn, authorColumn, priceColumn);
		
		// 데이터를 세팅하면 돼요
		tableView.setItems(list);
		
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene); // 화면이 붙음
		primaryStage.setTitle("Simple JavaFX TableView");
		
		primaryStage.show();
	
		
	}

	// 시작 포인트!
	public static void main(String[] args) {
		launch(); // 창 띄움
	}
}
