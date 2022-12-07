package sampleproj;

class ThreadEx_02_1 extends Thread {
	
	public ThreadEx_02_1() {
	}
	public ThreadEx_02_1(String name) {
		super(); // 상위 클래스인 Thread 클래스에서 문자열 하나를 받아들이는 생성자를 호출하고, Thread의 이름을 name으로 변경한다.
		this.setName(name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			System.out.println(this.getName());
		}
	}
}

public class ThreadEx_02 {
	
	public static void main(String[] args) {
		Thread t1 = new ThreadEx_02_1("My-Thread");
//		t1.start();
		t1.run(); // 얘는 쓰레드로 수행하는 거 아니야. 메소드 수행이고, blocking으로 실행돼.
		System.out.println("main thread 종료!");
	}
}
