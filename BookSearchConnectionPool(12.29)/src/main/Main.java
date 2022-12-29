package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
	
	private static BasicDataSource basicDS;
	
	// main이 호출되기 전에 특정 코드를 실행시키고 싶어요!
	// 일반적으로 프로그램에서 사용하는 resource 로딩하고 싶을 때 사용해요 
	static {
		// connection pool 만들거에요
		
		// database 연결
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("test1234");
		// database 연결된 connection 초기 사이즈 (10개 만들기)
		basicDS.setInitialSize(10);
		// 전체 connection pool안의 connection 최대 개수 (부족하면 더 만들기도 해서) 
		basicDS.setMaxTotal(20);
		
	}
	
	// private로 된 connection pool 사용하기 위해서 상위 interface인 DataSource 사용
	public static DataSource getDataSource() {
		return basicDS;
	}
	
	public static void main(String[] args) {
		
		// DBCP 사용에 대해서 알아보아요 
		// 만들고 사용하는 과정만 살짝 알아보아요
		
		
		DataSource ds = getDataSource(); // Connection pool 가져옴
		Connection con = null;
		
		try {
			con = ds.getConnection(); // pool 안의 connection pool 빌려옴
			String sql = "SELECT btitle, bauthor FROM book";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("btitle"));
			}
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
