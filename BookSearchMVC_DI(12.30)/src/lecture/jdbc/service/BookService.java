package lecture.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.dao.DBCPConnectionPool;
import lecture.jdbc.vo.BookVO;

// 로직 담당하는 class
public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String text) {
		// 검색 keyword를 받아서 ObservableList<BookVO>를 리턴하는 하나의 작업(transaction)을 처리.
		// 이 작업을 하기 위해서 로직 처리가 필요하고(더하고 빼고, for, if...)
		// 당연히 데이터베이스 처리가 필요
		// 그런데 우리 문제는 워낙 간단해요. 그냥 단순히 데이터베이스 테이블 뒤져서 결과 가져오면 끝남
		// 그럼 데이터베이스 처리를 해 보아요
		// 여기에서 데이터베이스 처리하면 안돼요 (DAO에서 처리) (Data Access Object)
		
		BookDAO dao = new BookDAO(); // dao가 하는 작업은 CRUD, 그걸 엮어서 로직을 만드는 게 service
		ObservableList<BookVO> list = dao.select(text);
		
		return list;
	}

	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		// 로직처리해야해요!
		// 지금은 DB 처리만 하면돼요
		
		// 여기서부터 Transaction 시작이에요!!
		// Connection에 대해서 setAutocommit() false로 지정해야 Transaction이 시작
		
		// 여기에서 Database Connection을 얻어와야해 
		try {
			Connection con = (DBCPConnectionPool.getDataSource()).getConnection();
			con.setAutoCommit(false); // transaction 시작
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		BookDAO dao = new BookDAO();
		
		int count = dao.delete(deleteISBN); // 지우기
		ObservableList<BookVO> list = dao.select(searchKeyword); // 검색해서 결과 가져오기
		
		// 여기가 Transaction 끝이에요!!
		// 서비스의 method 맨 마지막에 이 메소드가 잘 처리되었으면
		// -> 이 로직 코드는 우리가 처리해야함
		// transaction을 commit 해야하고 그렇지 않으면 rollback 해야해요.
		
		if (count ==1 && list != null) {
			con.commit();
		} else {
			con.rollback();
			}
		return list;
	}

	
}
