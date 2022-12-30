package lecture.jdbc.di.step3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class UserDAO {
	
	// 추상 메소드로 만들어요	
	// 다른 class에서 가져다 써야하지만
	// public으로 잡으면 너무 access 범위가 넓어져서
	protected abstract Connection getConnection(); 
			
	
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
