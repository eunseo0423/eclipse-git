	// 실습 연습용
package bank;


public class Customer {
	// 1. 생성자
	public Customer() {
		
	}
	
	// 2. field들 
	
	// 이름
	public String customerName;
	// 계좌번호
	public String accountNumber;
	// 잔액
	public long balance;
	
	// 3. method들
	
	// 잔액을 확인한다
	public long getBalance() {
		return this.balance;
	}
	
	// 입금한다
	public void deposit(long money) {
		this.balance += money;
	}
	
	// 출금한다
	public long withdraw(long money) {
		if(this.balance < money) {
			System.out.println("출력불가");
		} else {
			this.balance -= money;
		}
		return this.balance;
	}

}
