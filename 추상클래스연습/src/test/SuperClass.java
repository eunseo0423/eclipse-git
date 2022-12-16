package test;

public abstract class SuperClass {

	// 생성자
	public SuperClass() {
		
	}
	
	// field
	
	// method
	// 일반 method (method 정의가 나옴)
	public void sayHello() {
		System.out.println("안녕하세요.");
	}
	
	// method 선언 => 완전한 형태의 method가 아님 => abstract keyword를 이용해서 표시 
	public abstract void eat(); // 어떤 일을 하는 지 안나옴. 추상 method
}

class SubClass extends SuperClass {
	// 상속받았을 때 반드시 재정의 해야함 -> 추상 클래스의 의의
	@Override
	public void eat() {
		
		
	}
	
}