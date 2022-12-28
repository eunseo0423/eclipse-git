// Connection을 필요할 때 사용하고 필요없을 때 끊는 방법으로 수정

package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
//import javafx.util.Callback;
import lecture.jdbc.vo.BookVO;

public class BookSearchJavaFX_ConnectionChange extends Application {

//		TextArea textArea;
		// TextArea 영역에 TableView 붙일 것
		// TableView안에 데이터를 표현할 때 VO를 가져다가 한 줄 씩 표현하게 돼요
		// 그 때 어떤 VO를 사용하는지에 대한 class이름을 generic으로 지정해야 해요
		TableView<BookVO> tableView; // 어떤 VO 사용할 것인지 지정해준 것
		
		TextField textField;
		Button deleteBtn;
		
		String deleteISBN; // 삭제할 책 번호
		
		
		// 기본 생성자, JDBC 준비 단계를 여기서 하면 되겠네요~
		public BookSearchJavaFX_ConnectionChange() {
			
			// 1. JDBC Driver Loading 단계
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
		@Override // 우리 class에 내부적으로 객체가 만들어짐 (launch를 하면서)
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
			
			// 이벤트 처리 (JDBC 3~5단계)
			textField.setOnAction(e -> {
//				System.out.println("이벤트 발생되나요??"); // 제대로 작동하는지 확인용 
				
				String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
				String id = "root";
				String pw = "test1234";
				// 2. Database 접속
//				Connection con = DriverManager.getConnection(jdbc_url, id, pw); 
				
				// 지역변수가 되면서 이 영역내에서만 사용이 가능함..그래서 connection은 여기 있으면 안됨 ->field로 올리기
				
				
				// JDBC 3~5단계
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT bisbn, btitle, bauthor, bprice ");
				sql.append("FROM book ");
				sql.append("WHERE btitle like ?");
				sql.append("ORDER BY bprice DESC");

				Connection con = null;
				
				PreparedStatement pstmt;
				try {
					con = DriverManager.getConnection(jdbc_url, id, pw);
					pstmt = con.prepareStatement(sql.toString());
					// 실행하기 전에.. ? 를 채워야 해요!
					pstmt.setString(1, "%" + textField.getText() + "%"); // textField안의 값 가져오기
					
					ResultSet rs = pstmt.executeQuery();
					
//					ArrayList<Book> list = new ArrayList<Book>();
					// TableView는 ObservableList 사용
					ObservableList<BookVO> list = FXCollections.observableArrayList();
					
					// 5. 결과처리!
					while(rs.next()) {
						BookVO book = new BookVO(rs.getString("bisbn"),
								rs.getString("btitle"),
								rs.getString("bauthor"),
								rs.getInt("bprice"));
						list.add(book);
					
					}
					
					tableView.setItems(list);
					
					// 6. 사용한 자원 반납
					rs.close();
					pstmt.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			});
			
			
			// 삭제버튼도 만들어서 붙여요
			deleteBtn = new Button("선택된 책 삭제");
			deleteBtn.setPrefSize(150,40);
			deleteBtn.setDisable(true); // 사용할 수 없도록 설정(true) 있지만 클릭이 안되는 상태로 만듦
			deleteBtn.setOnAction(e -> {
				// 버튼을 누르면 여기가 실행돼요
				String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
				String id = "root";
				String pw = "test1234";
				
				Connection con = null;
				
				// 삭제 기능
				
				String sql = "DELETE FROM book WHERE bisbn = ?"; 
			    PreparedStatement pstmt;
				try {
					con = DriverManager.getConnection(jdbc_url, id, pw);
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, deleteISBN);
					
				    pstmt.executeUpdate();
				    
				    // 검색다시
				    StringBuffer sql2 = new StringBuffer();
					sql2.append("SELECT bisbn, btitle, bauthor, bprice ");
					sql2.append("FROM book ");
					sql2.append("WHERE btitle like ?");
					sql2.append("ORDER BY bprice DESC");
					
					PreparedStatement pstmt2;
					pstmt2 = con.prepareStatement(sql2.toString());
					pstmt2.setString(1, "%" + textField.getText() + "%");
					
//					pstmt2.executeQuery();
					
					ResultSet rs = pstmt2.executeQuery();
					
					ObservableList<BookVO> list = FXCollections.observableArrayList();
					
					// 5. 결과처리!
					while(rs.next()) {
						BookVO book = new BookVO(rs.getString("bisbn"),
								rs.getString("btitle"),
								rs.getString("bauthor"),
								rs.getInt("bprice"));
						list.add(book);
					
					}
					
					tableView.setItems(list);
//					con.rollback();
					
					// 6. 사용한 자원 반납
					rs.close();
					pstmt.close();
					con.close();
					
				    
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
				
			});
			
			
			flowpane.getChildren().add(textField);
			flowpane.getChildren().add(deleteBtn);
			
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
//			ObservableList<BookVO> list = FXCollections.observableArrayList();
			
//			ArrayList<BookVO> list = new ArrayList<BookVO>();
//			list.add(new BookVO("123", "java 30일 완성", "홍길동", 2000));
//			list.add(new BookVO("456", "java 이제 그만", "신사임당", 5000));
//			list.add(new BookVO("789", "어려워요", "강감찬", 8000));
			
			
			
//			textArea = new TextArea();
			tableView = new TableView<BookVO>();
			
			// tableView에 위에서 만들어진 column 객제 붙이기
			tableView.getColumns().addAll(isbnColumn, titleColumn, authorColumn, priceColumn);
			
			// 데이터를 세팅하면 돼요
//			tableView.setItems(list);
			
			
			// 나중에 TableView에 데이터가 표현이될거에요
			// 이 때 각 행들에 대해서 이벤트를 설정할 수 있어요.
			// 정확하게 얘기하자면 각 행들에 대한 특정 설정을 할 수 있어요!
			tableView.setRowFactory(e -> {
				// TableRow(테이블의 각 행을 만들어서)를 만들어서
				TableRow<BookVO> row = new TableRow<>(); // 행을 만들음
				// 해당 행의 이벤트 처리를 설정한 다음
				row.setOnMouseClicked(e1 -> { // row 에 대해 마우스로 틀릭하면
//					System.out.println("행을 클릭했어요!"); // 확인
					deleteBtn.setDisable(false); // 삭제버튼 활성화
					// 내가 어떤 행을 클릭했는지 확인을 해야하니
					BookVO book = row.getItem(); // 현재 row의 데이터를 갖구있는 VO가 튀어나옴
//					System.out.println(book.getBtitle()); // 클릭한 책 제목
					// 삭제할 책의 ISBN을 버튼이 클릭되었을 때 알아내야 해요
					deleteISBN = book.getBisbn();
				}); 
				// 해당 행을 리턴하는 방식
				return row;
				
			});
			
			
			root.setCenter(tableView);
			root.setBottom(flowpane);
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene); // 화면이 붙음
			primaryStage.setTitle("BookSearch JavaFX TableView");
			
			primaryStage.show();
		
			
		}

		// 시작 포인트!. 그 이상의 작업은 실행하지 않음
		public static void main(String[] args) {
			launch(); // 창 띄움
		}
	}
