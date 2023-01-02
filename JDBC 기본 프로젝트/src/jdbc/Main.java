package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			// 1. JDBC Driver Loading 단계
			Class.forName("com.mysql.cj.jdbc.Driver"); // 이름 갖고 class 찾아서 로딩하세요. package명과 class명 같이 명시
			System.out.println("Driver Loading 성공!"); // Driver Loading은 프로그램 내에서 딱 한번만 하면됨
			// jdbc 를 이용해서 mysql에 접속할거에요
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"; 
													  //사용database명
			// ID와 Password
			String id = "root";
			String pw = "test1234";
			
			// 2. Database 접속
			Connection con = DriverManager.getConnection(jdbc_url, id, pw);
			System.out.println("데이터베이스 접속 성공!!");
			
			// 일반 statement 사용
			// 3. Statement 생성
//			Statement stmt = con.createStatement();
			// 4. Query를 작성해서 실행시킬 거에요!
			// SELECT 구문 샐행
//			String sql = "SELECT bisbn, btitle, bauthor, bprice FROM book";
			String keyword = "자바";
//			String sql = "SELECT bisbn, btitle, bauthor, bprice FROM book WHERE btitle like '%" + keyword +"%'";
//			ResultSet rs = stmt.executeQuery(sql);
			
			// prepared Statement 사용
			// prepared Statement는 SQL을 가지고 생성해요
			// prepared Statement는 IN Parameter를 이용할 수 있어요 => ?로 표현해요!
			// 주의해야 하는 점은 keyword부분에는 ?(IN Parameter)를 쓸 수 없어요! 조건에 값을 대입할 때만 가능. 컬럼명에 ? 안됨
			String sql = "SELECT bisbn, btitle, bauthor, bprice FROM book WHERE btitle like ?"; // ? = 아직 다 채워지지 않았어요
			PreparedStatement pstmt = con.prepareStatement(sql); // connection 가지고 만듦!
			// 실행하기 전에 .. ?(IN Parameter) 채워야함
			pstmt .setString(1, "%" + keyword + "%"); // 첫 번째 물음표에는 이것을 대입
			
			ResultSet rs = pstmt.executeQuery(); // sql을 이미 가지고 있어서 괄호 안에 sql 필요없음
			
			
			// 5. 결과처리!
//			rs.next();
//			String title = rs.getString("btitle"); // 가리키는 곳의 문자열 갖구오세요. column명 사용
//			String title = rs.getString(3); // 숫자로도 가능하지만 일반적으로는 컬럼명을 해주는 게 좋아요
//			int price = rs.getInt("bprice"); // 꼭 맞춰줄 필요는 없지만 가능한한 type을 맞춰주는게 좋아요
//			System.out.println("책 제목은 : " + title + ", 책 가격은 : " + price);
			
//			rs.next();
//			String title = rs.getString("btitle"); // 다음 책 나옴
			// while문으로 책 전체 제목 갖구오기
			while(rs.next()) {
				String title = rs.getString("btitle");
				System.out.println("책 제목은 : " + title);
			}
			
			// 6. 사용한 자원 해제
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 없는 경우 대비해서 try catch 강제됨
		  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
