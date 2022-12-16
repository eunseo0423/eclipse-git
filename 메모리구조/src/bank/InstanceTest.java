// Static의 의미
package bank;

// javac.InstanceTest.java => compile한후
// java bank.InstancTest => JVM 이용해서 실행
public class InstanceTest {

	// 1. 생성자
	public InstanceTest {
		
	}
	
	// 2. field
	// static이 붙고 안붙고에 따라서 공간이 어디에 만들어지냐가 달라짐
	
	// class variable : class만 있으면 class 이름으로 사용할 수 있는 변수
	static int a = 100; // a라는 공간이 만들어지고 100이 들어감
	// a의 공간이 생성되는 곳은 Method Area 
	// instance를 만들지 않고도 사용을 할 수 있다
	
	// instance variable : instance가 있어야지 사용할 수 있는 variable
	int b = 200; // 인스턴스가 생성되면 200 채우라는 내용이 들어간것
	// b는 instance가 생성된 후에 그 안에 공간이 생성됨
	// 공간이 생성되는 곳은 heap
	
	
	// 3. method
	
	// 객체(instance)가 있어야 쓸 수 있음
	public void instanceCall(String msg) {
		System.out.println(msg);
	} 
	// static은 instance가 없어도 쓸 수 있음 
	public static void staticCall(String msg) {
		System.out.println(msg);
	} 
	
	// main에 대한 실행코드. main부터 시작. main 호출 -> main()을 위한 공간이 Stack에 생김
	public static void main(String[] args) {
		int k = 100; // local variable(지역변수) => stack에 저장되고
					 // 메소드가 종료하면 당연히 없어짐(메소드가 수행하는 동안에만 존재하는 변수)
					 // stack 안에 있는 건 다 지역변수
		InstanceTest hong = new InstanceTest(); // hong은 지역변수 참조변수 
		// hong에는 메모리 주소값에 대한 hash값이 들어감(참조변수)
		// Heap에 객체가 만들어짐 시작번지 300번지. hong에 300 들어감
		// field를 어떻게 만들 -> b가 instance 안에 만들어지고 200이 박힘
		// a가 있지만 static이 있어 공간이 있다. a라는 공간이 instance에 만들어지지만 이미 있어서 링크(주소값)(가리킴)
		// instanceCall, staticCall 다 method area 내 class에 링크를 연결시켜줌
		hong.a = 300; // instance를 이용해서 static field를 이용할 수 있음.
		InstanceTest.a = 500 // static field이기 때문에 instance가 아닌 class를 이용해서 사용할 수 있음.
		
	    hong.b = 300; //ok
//		InstanceTest.b = 500; //error
	}
}
