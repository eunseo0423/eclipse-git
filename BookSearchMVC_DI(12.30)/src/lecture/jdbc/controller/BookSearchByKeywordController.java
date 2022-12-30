package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class BookSearchByKeywordController {

	public ObservableList<BookVO> getResult(String text) {
		// Controller의 역학은 view와 service (model) (logic 처리) 과의 연결
		// service객체가 있어야지 일을 시키겠죠? -> service class 만들러 가기
		
		BookService service = new BookService();
		ObservableList<BookVO> list = 
				service.selectBooksByKeyword(text); // 지금 view가 하려는 일의 transaction(작업의 최소단위) 
													// 이름자체가 transaction을 지칭하는 이름이어야 함
													// text도 가져왕
		
		return list;
	}

	
}
