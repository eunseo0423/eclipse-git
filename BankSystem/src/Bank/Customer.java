package Bank;


public class Customer {
	
	// 1.생성자
	// 생성자가 같은 package 안에 있는 다른 class에 의해서 사용된다면..
	// public keyword를 필요없음
	// 만약 생성자가 다른 package 안에 있는 다른 class에 의해서 사용된다면..
	// public keyword가 필요해요
	// 지금 우리는 같은 프로젝트안에 다른 package에서 customer class의 
	// instance를 생성할 예정임. 따라서 생성자에 public을 붙여야함
	
	public Customer() {
		// default 생성자. 아무런 일도 하지않고 아무런 인자(parameter)를 받지 않는 생성자
		// 만약 내가 작성하지 않았으면 자동으로 default 생성자 삽입
	}
	// 그런게 생성자는 1개 이상 있을 수 있다고 했는데, 이름이 같아서 차이를 둬야함
	// 하나의 클래스 안에 생성자의 이름은 모두 동일하기 때문에 인자의 개수 혹은 인자의 타입으로
	// 생성자를 구분해야한다
	// 생성자는 만들어진 instance의 초기화를 담당
	// 인스턴스의 필드 초기값을 설정하는 일을 함
	public Customer(String name) {
		this.name = name;
	}
	
	// 2. field 선언
	public String name;		// 고객이름  => instance variable
	public String accountNumber; // 계좌번호 => instance variable
	public long balance;  // 잔액 => instance variable
	// field가 모두 instance variable은 아님
	
	// 3. method 정의
	// 잔액을 확인한다 라는 기능을 만들거임 => 잔액을 알아내서 리턴한다. 
	// - 기능 자체가 잔액을 조사해서 출력까지 진행할건지
	// - 기능은 잔액을 조사해서 잔액의 값만 리턴시키고 출력은 따로 수행할건지
	public long getBalance() {
		return this.balance; 
		// this는 변수. 그 안에 메모리 주소값이 들어있음. instance에 대한 reference 주소가 들어있음
		// 현재 사용하는 객체에 대한 주소가 들어있게 됨
	}
	
	// 입금하는 기능(리턴값이 없는 형태의 method로 만들 것)
	public void deposit(long money) {
		this.balance += money; // this.balance = this.balance + money;
	}
	// void = 리턴값이 없는
	
	// 출금하는 기능(출금 후 잔액을 리턴값으로 사용)
	public long withdraw(long money) {
		// 만약 잔액이 출금요청액보다 적으면 출금이 되면 안됨
		if(this.balance < money) {
			System.out.println("출금불가");
		} else {
			this.balance -= money;
		}
		return this.balance;
	}
	
	
}
