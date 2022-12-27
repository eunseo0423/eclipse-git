package jdbc.vo;

// VO는 데이터를 표현하는 객체에요
// 이 안에 Business Logic 들어오면 안됨. 
// 값을 저장하고 값을 표현하기 위한 객체
// Database의 Table을 참조해서 Table의 column을 기준으로 만듦

public class Book {

	private String bisbn; // table의 column명과 똑같게 잡기 
	private String btitle;
	private String bauthor;
	private int bprice;
	
	// 기본 생성자
	public Book() {
		
	}

	public Book(String bisbn, String btitle, String bauthor, int bprice) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bprice = bprice;
	}

	public String getBisbn() {
		return bisbn;
	}

	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
	
	
	@Override
	public String toString() {
		return bisbn + ", " + btitle + ", " + bauthor + ", " + bprice;
	}
	
}
