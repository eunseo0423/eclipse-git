package lecture.jdbc.di.step3;

public class Main {

	public static void main(String[] args) {
		
		User user = new User("hong", "홍길동", "1234"); // 사용자 객체 만들었어요
		
		UserDAO dao = new KosaUserDAO();
		
		dao.insert(user);
		
		System.out.println("새로운 사용자 등록!");
		
		User user2 = dao.select("hong"); // hong의 VO return 받음
		
		System.out.println(user2.getName() + "," + user2.getPassword());
		System.out.println("조회성공!");
	}
}
