package bank;

public class Customer {
	
	// 1.constructor(생성자)
	// default constructor : 인자도 없고 하는 일도 없는 생성자
	public Customer() {
		// 객체 만들고 초기화 안하겠다
	}
	
	// 2. field
	public String name;
	public long balance;
	
	// 3. method
	public void deposit(long money) {
		int myMoney = 100; // 예제를 확인하기 위해 임시로 넣은 것
		this.balance += money;
	}
}
