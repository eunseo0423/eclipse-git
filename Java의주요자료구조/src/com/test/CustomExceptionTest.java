package com.test;

class MyClass extends Exception {
	
}

public class CustomExceptionTest {

	public static void main(String[] args) throws Exception {
		System.out.println("시작");
		
		throw new MyClass();  // exception 객체가 생성됨 
						// 객체를 만드는거랑 익셉션을 발생시키는 건 다름
						// 이런 exception 객체를 던져야 됨. 앞에 throw 붙이기
						// 의도를 가지고 수동으로 exception 발생시킨 것
						// 그럴때는 throw를 명시해야함
		
		System.out.println("끝");
	}
}
