package test;

// Java test.student => 1. method area에 class 정보를 올림
					//	2. Student class 안에서 main을 찾아요 (만약에 없으면 Error)
					//     만약 있으면 JVM에 의해서 main(method)이 호출 됨, method를 호출하려면 객체가 있어야함(객체 없이 실행하려면 static)
					// 	3. stack에 main method를 위한 공간 할당. 
// 객체지향의 상속을 이용해서 클래스 다시 만들기
// Java에서 extends라는 keyword 제공
// extends 뒤에 class가 나올 수 있는데 Java는 이때 단 하나의 class만 기입할 수 있음
// Java는 다중상속(확장을 조금 더 쉽게 할 수 있음)은 지원하지 않음
// Java는 단일상속을 지원

public class Student extends Person {
	// 추가적으로 만들 것들만 작성하면 됨
	
	public Student() {
		super();
		// default constructor 
		// 상위 클래스 constructor 호출
//		super("홍길동"); // 만약 이 코드가 없으면 자동으로 삽입
				 // 상위 class의 생성자를 호출하는 코드
				 // Person() (인자가 없음) 이 형태의 생성자를 호출
				 // Person("홍길동")과 같은 의미 . 상위클래스와 맞춰줘야함
		// 현재 객체의 field를 초기화하는 부분
	}
	
	// field
	private String dept; // 학과
	
	// method
	
	// 상속 받은 상위 클래스의 메소드를 
	// 하위 클래스에서 재정의(다시 정의) 하는 거
	// method overriding(메소드 오버라이딩)
	public void eat() {
		super.eat(); // 상위 type의 method를 호출 할 수 있어요. -> 밥을 먹어요
		System.out.println("밥을 신나게 먹어요");  			 // 밥을 신나게 먹어요
	}
	
	public void study() {
		System.out.println("공부를 해요");
	}
	
	
	// student 라는 class의 instance 만들 것
	public static void main(String[] args) {
		// Student 대신 Person 쓸 수 잇음(IS-A관계에 의해)
		Person s = new Student();
		// data type => 지정된 변수안에 들어올 값에 대한 제한을 걸어요.
		// Person s 라고하면 s 가 Person 시작주소 가리킴(어떤 객체를 사용할건지 정할 수 있음, data type의 역할)
		// IS-A 관계에 의해서 할 수 있는 것
		
		s.eat(); 
		
	}
}
