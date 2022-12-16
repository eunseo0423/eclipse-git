package test;

public class ThisTest {

	// 1. constructor(생성자)
	// default constructor는 항상 명시적으로 작성해 주세요
	public ThisTest() {
		this(100); // this()는 현재 class의 다른 생성자를 호출할 때 사용
				   // 숫자를 받아들이는 생성자 호출 
	}
	
	// constructor overloading으로 또 다른 생성자를 하나 만들어요.
	public ThisTest(int k) {
		super();
		System.out.println("인자가 하나 있는 생성자!");
	}
	
	// field와 method는 따로 존재하지 않음
	
	
	public static void main(String[] args) {
		ThisTest a = new ThisTest();
	}
}
