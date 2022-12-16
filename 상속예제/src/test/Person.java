package test;
//import java.lang.*; // 기본적으로 생략이 가능


public class Person { // extends Object 생략되어 있음

	// 1. constructor
	public Person() {
		
	}
	
	public Person(String name) {
		super(); // 현재 class의 상위 class인 Object class의 생성자를 호출
				 // Object()가 호출돼서 실행
		// 인자도 받아들이지 않고 하는 일도 없는 constructor = default constructor
		// 이쯤에서 공간이 생성되게 됨
		// 자신의 초기화를 진행해야 함.
		
//		System.out.println("Person"의 생성자가 호출);
//		this.name = name
		
		
	}
	
	// 2. field
	// 사람이 가지는 특성들 변수화
	// 일반적으로 private
	private String name;
	private String gender;
	private int age;
	
	// 3. method
	// business logic method
	// 일반적으로 public
	// method는 보통 필드에 있는 데이터를 핸들링, 제어
	public void eat() {
		System.out.println("밥을 먹어요"); // 이건 그냥 예시로 
	}
}
