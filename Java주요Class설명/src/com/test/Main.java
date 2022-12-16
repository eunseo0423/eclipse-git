package com.test;

public class Main {

	public static void main(String[] args) {
		
		Student s1 = new Student("홍길동", 20);
		Student s2 = new Student("홍길동", 20);
		
		// 두 객체가 같은 객체인가요? (의미)
		// 1. 진짜 두 개의 instance가 같은 메모리 공간을 공유하고 있나요?
		// 2. instance의 내용이 같은가요?
		
		// 일반적으로 똑같니? 라고 물어보는 이 연산자는 "==" => 비교연산자
		// 이 연산의 결과는 true, false 논리값으로 결과가 리턴돼요. 
		
		System.out.println(s1 == s2); // false
		System.out.println(s1.equals(s2)); //비교를 위해 제공되는 equals . true
		
		System.out.println(s1.toString()); // com.test.Student@5e91993f 
										   //-> 우리에게 큰 의미가 없는 값이므로 overriding해서 바꿔주기
										   // 홍길동, 20
	}
}
