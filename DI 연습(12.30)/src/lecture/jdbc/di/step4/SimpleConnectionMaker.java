package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {

	// 연결정보를 가지고 connection을 생성하는 method를
	// 이 class 안에 작성하자
	
	public Connection getConnection() {
		// 자기 나름대로의 database 사용 준비코드 넣어주면 돼요
		// connection을 리턴하는 코드를 작성하면 돼요
			
		Connection con = null;
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
				
			con = DriverManager.getConnection(jdbc_url, id, pw);
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return con;
			
	}
}
