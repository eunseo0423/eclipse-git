package bank;

public class InstanceTest2 {

	// field
	static int a = staticCall("1번째 출력입니다."); // 이 함수가 실행되고 그 결과값이 들어가는 것
	// 그래서 함수도 static 이어야함
	// static 필드에 있는 것은 method도 static 
	
	int b = staticCall("2번째 출력입니다.");
	
	// method
	public static int staticCall(String msg) {
		System.out.println(msg);
		return 100;
	}
	
	// static block
	// 이 static block은 언제 실행되나요?
	// main method 이전에 실행 
	static {
		System.out.println("static block 입니다. a의 값은 : " + a);
		// 프로그램에서 사용되는 라이브러리를 미리 로딩하고자 할 때. 
		// JNI(다른언어로 만든 라이브러리 불러서 자바 프로그램과 연동할 때)		
	}
	
	// 생성자
	public InstanceTest2() { 
		this.b = staticCall("3번째 출력입니다.");
	}
	
	public static void main(String[] args) {
		System.out.println("4번째 출력입니다.");
		int c = staticCall("5번째 출력입니다.");
		InstanceTest2 d = new InstanceTest2();
	}
	
} // 1 4 5 2 3 
