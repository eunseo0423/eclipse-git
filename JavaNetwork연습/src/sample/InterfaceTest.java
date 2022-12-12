package sample;

interface myInterface {
	
	void aa(); // public abstract 매번 붙으니 생략
}

// 그동안은 이렇게 명시적으로 class를 만들어줌
//class MyClass implements myInterface {
//
//	@Override
//	public void aa() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}

public class InterfaceTest {	
	
	public static void main(String[] args) {
//		myInterface a = new myInterface(); 안됨.
		// 인터페이스는 class의 아주 특별한 형태여서 
		// 인터페이스를 이용해서 instance를 만들 수 없음
		
		myInterface a = new myInterface() { // 실제 인터페이스 인스턴스 만들면서 오버라이딩 하면서 눈에 보이지 않는 class를 만들면 됨
											// 많이 쓰는 방법!
			@Override
			public void aa() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
	}
}
