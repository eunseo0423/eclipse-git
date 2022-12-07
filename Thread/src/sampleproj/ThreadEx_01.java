package sampleproj;

// 얘는 Thread다. is-a 관계에 의해..
class ThreadEx_01_1 extends Thread {

	// 새로운 독립적인 실행 흐름이라매...
	// 프로그램의 entry point에 준하는 시작 method가 있다는 의미
	// 쓰레드에서는 main이 아니야. run이야.
	// run은 Thread가 가지고 있어. 그래서 여기서 오버라이딩(재정의)하는 거야.
	@Override
	public void run() {
		// 내가 실행하고 싶은 코드를 작성!
		// for문 같은 경우..
		// 1. 집합자료구조를 이용하는 경우 -> for ~ each 구문
		// 		for (String s : list) { }
		// 2. 원하는 횟수만큼 반복할 경우 -> 일반 for문
		for (int i = 0; i < 5; i ++) {
//			System.out.println(this.getName()); // 현재 만들어진 쓰레드의 인스턴스를 가리키는 경우, this를 활용할 수 있다. 여기서는 this를 생략 가능.
			System.out.println(getName()); // 여기서는 this를 생략 가능.
		}
	}
}

// 얘는 Thread가 아니다. 단지, Runnable이라는 특별한 interface를 구현한 클래스일 뿐이다.
// 그래서 getName() method를 이용하려면 현재 실행중인 쓰레드를 찾는 코드가 필요하다.
class ThreadEx_01_2 implements Runnable {
	
	@Override
	public void run() {
		
		for (int i = 0; i < 5; i ++) {
			System.out.println(Thread.currentThread().getName()); // getName()은 Thread 객체가 사용할 수 있다.
		}	
	}
	
}

// 이전까지 했던 설명
// java Thread Ex_01 실항 -> JVM이 main method를 호출해서 프로그램을 시작한다.

// Thread 관점에서 다시 설명
// java ThreadEx_01 실행 -> JVM이 내부적으로 Thread를 하나 생성
// -> 이 Thread가 main method를 호출해서 실행한다. 이 Thread를 main thread라고 부른다.(이름이 정해져있어)

// 예전 설명
// process(프로그램이라고 표현해도 된다)의 종료는 main method가 시작되면 프로그램이 시작하는 거고,
// main method가 종료되는 전체 프로그램이 종료된다. -> 엄밀히 말하면 틀렸음.

// 좀 더 정확하게 표현해보자
// process 안에 있는 모든 Thread가 종료되어야 process가 종료되는 것이다.
// main은 쓰레드 안에 있는 하나의 메소드일 뿐이야.

public class ThreadEx_01 {
	
	public static void main(String[] args) {
		// 이 main method는 당연히 하나의 Thread에 의해서 실행되는 method
		ThreadEx_01_1 t1 = new ThreadEx_01_1(); // t1은 Thread instance가 된다.
				// t1은 현재 단순 객체일 뿐이야. 아직 동작하지 않고 있다.
				// 이 thread는 독립적인 실행 흐름을 가지고 있다.
				// run() 안에는 그 내용을 기술하고 있다.

		// 이제 이 thread 를 실행시키자.
//		t1.run(); // 이렇게 호출하면.. 단순히 객체의 method를 호출하는 거지, Thread를 실행시킨 건 아니다.
				// 이렇게 하는 게 아니라, 다른 method를 이용해야 한다.
				// 여기서, run()은 method를 호출한 것이기 때문에, blocking 상태로 들어간다.
				// 거의 대부분의 method는 blocking method이다.
				// method 수행이 끝나고 return 될 때까지 대기!

//		t1.start(); // start()라는 메소드를 이용해서 Thread를 기동시켜야 한다.
				// start()는 nonblocking method이다.
		System.out.println("main thread 종료!!");

		Runnable r2 = new ThreadEx_01_2(); // interface도 상위 타입이니까 is-a 관계에 의해 선언 타입을 Runnable로 지정할 수 있다. 물론 얘는 Thread가 아니다.
		Thread t2 = new Thread(r2); // r2의 상위에 쓰레드를 넣어주기 위함. 즉, 이렇게 해야 r2가 Thread가 된다.
		
		t1.start(); // Thread-0
		t2.start(); // Thread-1
	}
}