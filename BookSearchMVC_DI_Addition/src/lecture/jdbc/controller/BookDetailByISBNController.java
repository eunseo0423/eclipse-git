package lecture.jdbc.controller;

import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class BookDetailByISBNController {

	public BookVO getResult(String bisbn) {
		// TODO Auto-generated method stub
		BookService service = new BookService();
		BookVO book = service.selectBookByISBN(bisbn);
		
		return book;
	}

}
