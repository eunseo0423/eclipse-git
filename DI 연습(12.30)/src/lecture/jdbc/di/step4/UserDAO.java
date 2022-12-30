package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 상속도 안되고
// 그렇다고 DAO 안에 데이터베이스 연결정보를 넣자니 이건 말이 안되고
// 연결정보를 class 단위로 분리하면 어때?

public class UserDAO {

	private SimpleConnectionMaker simpleConnectionMaker; 


	public UserDAO() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}
	
	// 회원가입
	public void insert(User user) { // VO가 전달이 돼서 DAO를 가지고 VO를 처리
				
		// 일반 JDBC Code가 나오겠죠
		try {
			Connection con = simpleConnectionMaker.getConnection();
					
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
					
			Connection con = simpleConnectionMaker.getConnection();
					
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
