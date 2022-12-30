package lecture.jdbc.di.step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 사용자에 관련한 데이터베이스 처리만 함
// DAO 안에는 Business logic(->service)이 나올 수 없어요
public class UserDAO {
	
	
	
	private Connection getConnection() {
		
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
	
	// 회원가입
	public void insert(User user) { // VO가 전달이 돼서 DAO를 가지고 VO를 처리
		
		// 일반 JDBC Code가 나오겠죠
		try {
			Connection con = getConnection();
			
			String sql = "INSERT INTO users VALUES (?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			
			pstmt.executeUpdate(); // 실행해서 사용자 등록 
			
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 회원정보 추출
	
	public User select(String string) {
		User user = null;
		
		try {
			
			Connection con = getConnection();
			
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, string);
		
			ResultSet rs = pstmt.executeQuery(); // 사용자 검색
			
			rs.next();
			
			user = new User(string, 
					rs.getString("name"),
					rs.getString("password"));
			
			rs.close();
			pstmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
}