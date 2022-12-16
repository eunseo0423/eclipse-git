package main;

import test.Customer;

public class Main {

	//프로그램의 시작포인트를 알려주는 
	public static void main(string[] args) {
		// 프로그램의 entry point
		// 이제 간단하게 instance를 class로부터 생성
		System.out.println("프로그램 시작!");
		
		Customer hong = new Customer(); // reference data type
		// hong => reference variable(참조변수)
		//         메모리 주소에 대한 찹조값(해쉬값)이 들어가 있기 때문에 이 변수를 이렇게 부름
		// instance는 메모리에 존재함(메모리 공간 중 어디에 위치하냐면 Heap이라고 불리는 영역에 존재)
		// 우리가 사용하는 건 결국 instance를 사용하는 것. 이 공간을 사용하기 위해 참조변수를 이용하는 것
		// 그래서 우리가 편하게(일반적으로) hong을 객체라고 얘기함(실제는 아님)
	    hong.balance = 1000;
	    
	    Customer shin = new Customer();
	    shin.balance = 2000;
	    //field는 instance variable . instance마다 각각의 공간이 따로따로 생성됨
	    
	    
	}
}
