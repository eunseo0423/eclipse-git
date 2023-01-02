package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import jdbc.vo.Book;
import jdbc.vo.Department;

public class Example01 {

	public static void main(String[] args) {
		
		try {
			// 1. Driver Loading
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Database 연결
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/mysql_test_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			Connection con = DriverManager.getConnection(jdbc_url, id, pw); 
			
			// 3. 실행할 SQL 문장을 가지고 있는 Statement 생성
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT category, department_name, capacity "); // 한 칸 띄우기
			sql.append("FROM tb_department ");
			sql.append("WHERE (category='공학') and (capacity between 20 and 30) ");
			sql.append("ORDER BY department_name ASC");
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			// 4. 실행 
			ResultSet rs = pstmt.executeQuery(); // rs는 포인터. 맨 처음에는 index 가리킴
			
			ArrayList<Department> list = new ArrayList<Department>();
			
			// 5. 결과처리!
			while(rs.next()) {
				// 6. VO 생성
				Department dept = new Department(rs.getString("category"), // Class 부터 만들어야 instance(VO) 생성 (Department class 생성)
						rs.getString("department_name"),
						rs.getInt("capacity"));
				// arraylist에 추가
				list.add(dept);
			}
			
			for(Department d : list) {
				System.out.println(d.getDepartment_name()+" "+d.getCategory()+" "+d.getCapacity());
//				System.out.println(d);
			}
			
			// 7. 사용한 리소스 해제
			rs.close();
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
