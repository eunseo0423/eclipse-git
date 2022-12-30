package lecture.jdbc.di.step5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

	private ConnectionMaker connectionMaker; // interface명 field명
	
	public UserDAO() {
		connectionMaker = new KosaConnectionMaker();  // instance 만들어주기.... 이게 문제 ( 해결 -> step6 )
													  // interface로 결합도를 많이 약화시킨 건 맞지만 이 class가 또 나옴 
													  // "class이름 이렇게 하셔야해요" 안내해야함 ㅠ -> 다시 결합도가 높아짐
	}
	
	
	// 회원가입
	public void insert(User user) { // VO가 전달이 돼서 DAO를 가지고 VO를 처리
					
		// 일반 JDBC Code가 나오겠죠
		try {
			Connection con = connectionMaker.getConnection();
						
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
						
			Connection con = connectionMaker.getConnection();
						
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
