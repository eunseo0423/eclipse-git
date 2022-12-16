package test;

public class Main {

	public static void main(String[] args) { //프로그램의 entry point로 사용
		// abstract class로부터 instance를 생성해 보아요
		// 안됨 
		SuperClass a = new SubClass(); // 재정의 안하면 객체화할 수 없음!
		// 이 class를 직접적으로 instance할 수는 없음 그럼 어떡하징..
		
	}
}
