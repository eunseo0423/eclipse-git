package bank;

public class Main {
	public static void main(String[] args) {
		
		// 여기서부터 프로그램 시작
		// 프로그램의 실행의 흐름은 하나 그 하나가 main 
		// 해당 실행 흐름이 끝나면 프로그램이 끝나게 됨
		// 나중에 실행 흐름이 여러개인 것이 나옴(thread)
		
		// hong은 지역변수(local variable)
		
		Customer hong = new Customer();
		hong.name = "홍길동";
		
		hong.deposit(2000);
	}

}
