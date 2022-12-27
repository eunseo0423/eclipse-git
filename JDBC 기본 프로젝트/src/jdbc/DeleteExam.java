package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExam {

	public static void main(String[] args) {
		// 특정 책을 지울거에요.
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		    String id = "root";
		    String pw = "test1234";
		    // 2. 데이터베이스 연결
		    Connection con = DriverManager.getConnection(jdbc_url, id, pw);
		    
		    con.setAutoCommit(false); // auto commit설정은 안하겠어 = transaction 시작!
		    
		    // 3. PreparedStatement 생성
		    String sql = "DELETE FROM book WHERE btitle like ?"; // 특정 키워드가 제목에 들어간 책을 지울 것
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, "%여행%");
		    
		    // 4. 실행
		    int count = pstmt.executeUpdate(); // 결과가 int로 옴. 리턴값은 정수값이 와요. 영향을 받은 row의 수
		    
		    // 5. 결과처리
		    System.out.println("삭제한 row의 수는 : " + count);
//		    con.commit(); // transaction을 종료하고 지금까지 실행한 SQL문을 실제로 데이터베이스에 적용
		    con.rollback(); // transaction을 종료하고 지금까지 실행한 SQL문을 무효화!
		    // 만약 transaction을 종료하지 않고 connection을 close하면 자동으로 SQL문을 실제로 적용
		    
		    // 6. 사용한 자원 반납
		    pstmt.close();
		    con.close();
		    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
