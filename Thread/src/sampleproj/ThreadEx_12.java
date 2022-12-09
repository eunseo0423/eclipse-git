package sampleproj;


// 공용객체 class 만들어요. 일반 class의 일반 객체.
class Account {
	// 생성자
	public Account() {
		
	}
	
	// 초기에 1000원 갖구있음. 그 생성자 생성
	public Account(int balance) {
		super();
		this.balance = balance;
	}
	
	// field
	private int balance;
	
	// getter&setter
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// method
	// 출금이라는 business method
	// synchronized method 동기화 메소드
	// 이 메소드를 실행한 Thread가 먼저 Lock(Monitor)획득
	// 먼저 진입한 Thread가 끝날 떄 까지 다른 Thread가 들어오지 못함
	// 하나의 Thread가 method를 호출하면 나머지 하나는 block돼요
//	public synchronized void withdraw(int money) {  -> method 전체 동기화
	public void withdraw(int money) {
		
		// 여기에 입력되는 코드는 동기화 안됨
		
		synchronized (this) { // 공용객체 기입 -> 일정코드를 임계영역으로 지정하려면 synchronized block 지정 (임계영역)
			if(balance >= money) { // 잔액이 출금하려는 돈보다 많다
				try {
					Thread.sleep(1000); // 제대로 출금처리(동기화처리) 됐는지 확인하기 위해서 재우기
				} catch (InterruptedException e) {
					
				}
				
				balance -= money;
			}
		}
		
		// 여기에 입력되는 코드는 동기화 안됨
			
	}
	
}

class ThreadEx_12_1 implements Runnable {
	
	// field
	Account acc = new Account(1000); // 1000원으로 초기화
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100; // 0~3 사이 실수 에다 1 더해서 0~4 실수 ->  정수 *100
			acc.withdraw(money); // 공용객체의 출금처리
			System.out.println("남은 금액 : " +acc.getBalance());
		}
	}
	
}

public class ThreadEx_12 {

	public static void main(String[] args) {
		ThreadEx_12_1 r = new ThreadEx_12_1();
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r); // Runnable 공유. Account acc 공유
		
		t1.start();
		t2.start(); // 공용객체를 2개의 thread가 경쟁적으로 출금하기 시작하면 - 뜨기도 하면서 공용데이터가 훼손되고 있는것
				    // 왜 그럴까? 2개가 동시에 진행돼서 
		
				
	}
}
