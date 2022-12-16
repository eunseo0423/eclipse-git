package main;

import Bank.Customer;
import bank.*;

public class Main {
	
	// 1.생성자
	// default 생성자가 들어와요
	
	// 2. field도 존재할 수 있어요 하지만 필요없죠
	
	// 3. method는 entry point인 main method만 존재
	
	public static void main(String[] args) {
		
//		// 1. 홍길동 instance를 생성해요
//		Customer hong = new Customer();
//		// 2. 생성한 객체에 이름을 홍길동으로 setting
//		hong.name = "홍길동";
		
		Customer hong = new Customer("홍길동");
		
		// public으로 잡으면 데이터를 보호해줄 수 있는 보호장치가 없음, 프로그램의 보안성이 떨어짐
		// 아주 특별한 경우를 제외하고는 field에 public을 붙이지 않음
		
		// 3. 홍길동이 잔액을 확인
		long result = hong.getBalance();
		System.out.println("잔액은 : " + result);
		
		// 4. 홍길동이 2000 입금함
		hong.deposit(2000);
		result = hong.getBalance();
		System.out.println("잔액은 : " + result);
		
		// 5. 홍길동이 3000 출금함
		long rest = hong.withdraw(3000);
		System.out.println("잔액은 : " + rest);
		
		// 6. 홍길동이 1000 출금함
		rest = hong.withdraw(1000);
		System.out.println("잔액은 : " + rest);
	}

}
