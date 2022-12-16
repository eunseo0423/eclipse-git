package test;

import java.lang.String; //이 패키지는 앞으로 패키지명 안쓰겠다
// import = 패키지명 안쓸게요~
// java.lang.* = 패키지 안의 모든 것 사용하겠다 (*=모든) 
// java.lang.* 내가 안쓰면 자동으로 삽입됨 그래서 String import 없이 그냥 사용 가능

//class 이름은 첫글자 대문자(Pascal case)
public class Customer { 

	// 순서 상관없음. 규격화 시킨 순서
	// 1. 생성자들
	// 클래스 안에 생성자를 이용해서 class로부터
	// instance 생성할 수 있음
	// 결국, 모든 class는 하나 이상의 생성자를 가지고 있음
	// 생성자가 없는 class는 존재하지 않음
	// 만드는 규칙이 있음
	// 일단 public(public을 쓸 수도 있고 아닐수도 있음) 
	// 생성자는 method가 아님 => 리턴타입이 존재하지 않음(method처럼생겼는데 리턴타입이 없으면 생성자)
	// 생성자의 이름은 클래스의 이름으로 정해져 있음.
	// 입력인자(parameter)를 가질 수 있음
	// 중괄호안에 instance 초기화 코드가 들어옴(어떤 방식으로 초기화할건지 입력)
	// 당연히 리턴구문은 존재하지 않음
	// 이렇게 만든걸 우리는 생성자(cunstructor)라고 함
	public Customer() {
		// 생성될 인스턴스의 초기화코드가 들어옴
		
	}

	
	// 2. field들
	// 변수들이 옴 => camel case notation
	// 이름
	String customerName;     // 고객이름
	public long balance;            // 잔액, int는 -21억~+21억 그래서 long으로
	String customerAccount;  // 고객 계좌번호
	int customerAge;         // 고객 나이
	// long은 primitive data type
	// String은 class.. 문자가 여러개 모인것. 
	// 우리가 만든 게 아니라 Java가 제공해준 class. 프로그램을 쉽게 하기 위해서 제공된 class
	// Java는 모든 게 class. 이런 class가 정말 많음(class library)
	// String은 Reference data type 
	// 이 많은 제공된 class는 당연히 package로 묶여서 제공됨
	// 그래서 package를 명시하고 사용해야함 (java,lang.String)
	// public은 패키지에 상관없이 사용가능. package로 묶으면 사용에 제한이 걸림. 그 제한을 풀어주는 게 public
	
	// 3. method들
	// 일반적으로 method는 특별한 용도가 아닌 이상 public을 기본으로 지정
	// 리턴타입 => 메소드는 우리가 알고있는 함수형태
	//           한수는 입력을 받아서 로직처리한 후 최종 결과를 만들어서(안만들수도있지만) 그 결과물을 생성해서 결과물을
	//           함수를 호출한 곳으로 돌려주기 위해서 사용(함수를 호출햇다는것은 일을 시킨 것)
	//           => 이 돌려주는 값을 리턴값
	//           이 리턴값이 어떤 데이터 타입인지를 method 정의할 때 선언. (결과물에 대한 데이터 타입이 리턴타입)
	public int getAge(int kk) {// 리턴타입 
		// kk는 parameter라고 하고 method의 입력을 받아들이는 역할을 함
		// business logic 처리가 진행됨
		System.out.println("나이를 알려주는 기능");
		// if,for,....
		return 30;
	}

}

