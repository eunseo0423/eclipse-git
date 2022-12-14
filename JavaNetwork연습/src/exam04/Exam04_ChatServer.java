// 내가 한 거 (진행중) => 12/14 수업
package exam04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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

// 8. 명시적으로 Thread 만들기. 이렇게 해도 됨 
// Runnable 추상class 구현을 위해 만듦
//class MyServerRunnable implements Runnable { // Thread 만들때 Thread 상속 거의 안함
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	} 
//	
//}

class Shared {
	// 공유객체 만들어지면 비어있는 ArrayList 하나 만들겠다
	
	ArrayList<Socket> list = new ArrayList<Socket>(); // field
	// 통로는 map 키는 socket
	HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	
	// method
	// 동기화를 걸어야해서 method륾 만들어서
	// 서버에 새로운 클라이언트가 접속하면
	// 해당 클라이언트에 대한 소켓이 서버쪽에 만들어지고 이 소켓을 공유객체에 저장해야해요.
	// 이 작업을 아래쪽에 있는 메소드가 수행할거에요
	// 소켓을 갖구와야 저장을하니까
	public synchronized void addClient(Socket socket) {
		list.add(socket);
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void broadcast(String msg) {
		// 전달받은 문자열을 모든 클라이언트 PriterWriter를 통해 데이터를 내보내요
		for(Socket s: list) {
			(map.get(s)).println(msg);
			(map.get(s)).flush();
			
		}
	}
	
}

// 11.
class MyRunnable implements Runnable {

	// 11-1. 소켓만들기
	// field로 소켓 박아주기
	Socket socket;
	// 클라이언트 15. 커넥트 마무리하고
//	PrintWriter pr;
	BufferedReader br;
	Shared shared;
	 
	// 기본생성자
	public MyRunnable() {
		
	}
	
	// 11-2. 소캣을 받아들여서 자기한테 세팅해주는 생성자 
	public MyRunnable(Socket socket, Shared shared) {
		super();
		this.socket = socket;
		this.shared = shared;
		
		// 클라이언트 15. 커넥트 마무리하고 통로 뚫음
		try {
//			this.pr = new PrintWriter(socket.getOutputStream());
			// 이제 공유객체가 출력하니까 위 코드는 필요없음. 공유객체가 통로를 갖구있음
			
			// 데이터를 받기만 하는 중
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		// 클라이언트 16. 에서 보내준 메세지 받고 보내기
		try {
			while(true) { // 무한루프해주기
				// (2) 데이터 받기 
				String msg = br.readLine();
				// (3) 데이터 보내기
//				pr.println(msg);
//				pr.flush(); // 보내고 Thread가 끝남 -> 계속적으로 반복해야함
				
				// 공유객체를 통해서 모든 클라이언트에게 데이터를 내보내요
				shared.broadcast(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

public class Exam04_ChatServer extends Application {
	
	TextArea textArea; // 대부분의 컴포넌트(입력상자, 텍스트 등)는 field로 잡는게 좋음
	// 4-4. 버튼 2개를 만듦
	Button startBtn;
	Button stopBtn;
	ServerSocket server;
	
	Shared shared; // 공유객체

	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception { 
		
		// 창의 화면 구성
		// primaryStage 화면에 뜨는 실제 window 창
		
		// 1. 화면을 구성할 Layout을 먼저 만들어요
		// - 화면 구성을 BorderPane을 이용해서 구성할거에요(동서남북중앙 화면 5등분)
		BorderPane root = new BorderPane(); // root에 붙이기 때문에 이름을 root로
		
		// 4. 실제 화면 구성
		// 4-2. root의 사이즈 정해주기
		root.setPrefSize(700, 500);
		
		// 4-1. Center
//		TextArea textArea = new TextArea(); 다른 곳에서 이 변수를 사용하려면(현재 지역변수) field로 올림
		textArea = new TextArea();
		root.setCenter(textArea);
		
		// 2. 이런 Layout을 이용해서 장면(Scene)을 만들어요!
		Scene scene = new Scene(root);
		
		// 3. 만들어진 장면을 window에 넣어요
		primaryStage.setScene(scene);
		
		// 4-4. 버튼 만들기
		startBtn = new Button("서버기동!");
		startBtn.setPrefSize(150, 40);
		// 5. 버튼의 이벤트처리
//		startBtn.setOnAction(new EventHandler<ActionEvent>() { // 클릭하면 handle이 호출
//			
//			@Override
//			public void handle(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});  => 너무 복잡한 코드
		// 코드 단순화(람다식) JBK8 이상부터 나옴
		startBtn.setOnAction(e -> {
			// 이벤트 처리 코드가 나와요. Java Lambda
			// 여기가 실행될 동안 window는 잠시 block (vovk사용자의 반응성에 문제됨 "잘못만들어졌네")
			
			// 공유객체를 생성
			shared = new Shared();
			
			
			
			// 7. 이벤트 처리 코드
			// 이벤트가 하필이면 client 접속을 무한으로 기다리는 코드 => 그래서 thread 사용
						
			// 8-1. Runnable 객체 만들기
//			MyServerRunnable r = new MyServerRunnable(); 
//			Thread t = new Thread(r);
//			t.start();
						
			// 9. ***이렇게 Runnable 구현하기!!!!!***
//			Thread t = new Thread(new Runnable() {
			//
//				@Override
//				public void run() { // 인자가 없음
//					// TODO Auto-generated method stub
//								
//				}
//							
//			});
						
//			Thread t = new Thread(() -> {}); // 위의 코드 람다로 축약해서 표현. run() method
//			t.start(); 
						
			// ***t를 굳이 쓰지 않아도 되기 때문에 이렇게 씀!!***
			(new Thread(() -> {
				// 10. Thread가 해야하는 내용 적어주기
				// 10-1. Server socket 하나 생성. 클라이언트 접속을 기다리는 존재
				try {
					// port가 다른 프로그램에서 사용되고 있는 중일 수 있기때문에 
					// 예외상황이 발생할 수 있어요. 그래서 이 코드는 예외처리가 강제돼요.
					server = new ServerSocket(7777); 
				// 10-2. accept만들기
//					server.accept(); // 클라이언트의 접속을 기다리며 Thread 일단 대기
					// 접속하면서 소켓 만들어지니까
					while(true) { // 12. 새로운 클라이언트를 다시 기다려야 하니까 반복문으로 묶어주기
						Socket socket = server.accept(); // 대기하고 있따가 클라이언트가 접속하면
						// 해당 클라이언트와 연결된 Socket 객체를 하나 생성해요
								
						printMsg("새로운 클라이언트 접속!");
						
						// 공유객체에 클라이언트 소켓을 저장해요!
						shared.addClient(socket);
									
						// 11. 클라이언트와 소켓을 이용해서 직접 클라이언트와 통신을 주관할 Thread 생성
						// 위의 Thread는 통신을 직접 하지 않음
						// Thread를 만들기 위한 클래스가 있어야함 
						// 정석의 방법으로 하는 이유? Thread가 소켓을 갖구있어서
						MyRunnable r = new MyRunnable(socket, shared); // 11-1. 에서 소켓만들어온 거 넣어주기. 이렇게 Thread안에 소켓이 있게됨
						Thread t = new Thread(r); //t 안에 소켓이 있어야함. t안에는 r이 있음.
						t.start(); 
					}
					//13. client 만들러가기
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			})).start();
		});
		
		
		stopBtn = new Button("서버중지!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> {
			
		});
		
		// 6. FlowPane
		// BorderPane의 아래부분(Bottom)에 버튼을 부착시키고 싶어요.
		// 공간은 하나인데 버튼은 2개 ㅠ 붙일수가 없넹..
		// 일단 FlowPane(판자)를 하나 만들어서 버튼 2개를 차례대로 붙이고
		// 이 판자를 BorderPane의 아래(Bottom)에 붙여요
		FlowPane flowPane = new FlowPane();
		// 붙일 떄 설정(패딩, 정렬 등)을 안하면 보기가 안좋아요(사용에 불편)
		
		// 6-1. FlowPane 설정
		flowPane.setPadding(new Insets(10, 10, 10, 10)); // padding (여백설정)
		flowPane.setColumnHalignment(HPos.CENTER); // 정렬
		flowPane.setPrefSize(700, 40); // 크기
		flowPane.setHgap(10); 
		
		// 6-2. 판자에 버튼 붙이기
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn);
		
		// 6-3. 판자를 bottom에 붙여요
		root.setBottom(flowPane);
		
		// 4-3. 창의 제목
		primaryStage.setTitle("Echo Server Program");
		
		// 0. 보여주기 코드 작성
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		// main thread에 의해서 최초로 실행되는 method
		// entry point
		// GUI Thread를 하나 생성할거에요
		
		launch(); 
	}
}
