package lecture.jdbc.di.step6;

public class Main {

	public static void main(String[] args) {
		
		User user = new User("hong", "홍길동", "1234"); // 사용자 객체 만들었어요
		
		ConnectionMaker cm = new KosaConnectionMaker();
		
		UserDAO dao = new UserDAO(cm); // 객체를 만들어서 의존성이 있는 UserDAO한테 밀어넣어줌. 객체를 이용해서 의존성을 만들음 "DI"
									   // 인스턴스가 인스턴스에게 줌. 객체간의 의존관계는 남아있음
		
		dao.insert(user);
		
		System.out.println("새로운 사용자 등록!");
		
		User user2 = dao.select("hong"); // hong의 VO return 받음
		
		System.out.println(user2.getName() + "," + user2.getPassword());
		System.out.println("조회성공!");
	}
}
