package com.test;

class Customer {
	// field
	public String name;
	public long balance;
	
}

public class ExceptionTest {

	public static void main(String[] args) {
		System.out.println("예외상황을 발생시켜요!");
		
		// 예시2
//		Customer a = null; // new Customer();
//				// null은 객체가 존재하지 않는다는 의미
//		a.name = "홍길동";
		// Exception in thread "main" java.lang.NullPointerException: Cannot assign field "name" because "a" is null
		// at com.test.ExceptionTest.main(ExceptionTest.java:18)
		// 없는 객체를 쓰려고 할 때 
		
		
		try { // throw 자동으로 진행
			int result = 10 / 0 ; // 문법적으로 오류가 없음. 0으로 나누면 무한대. 자바는 제공하지 않는..
							  // 하지만 runtime에 exception이 발생
							  // 숫자를 0으로 나누는 이상한 상황에 대한 class가 java는 이미 있음
		// 여기서 멈춤
		// Exception in thread "main" java.lang.ArithmeticException: / by zero
		// at com.test.ExceptionTest.main(ExceptionTest.java:7)
		} catch(NullPointerException e) {
			System.out.println("널포인터 익셉션!");
			// 원래는 여기에 예외상황처리코드가 나와야돼요.
		} catch(ArithmeticException e) { 
			System.out.println("수학연산 잘못됐어요");
		} finally {
			// 예외가 있건 없건 무조건 수행되는 코드
		}
		  // catch(Exception e) 는 모든 예외를 잡겠다는 의미
		  // Exception이 최상위 class니까
		  // catch는 위에서 부터 잡음. 상위 클래스가 catch 아래쪽에 있어서 위에서 못잡는거 아래서 잡아줌
		System.out.println("여기는 출력되나요?"); // 출력이 되지 않음
		
	}
}
