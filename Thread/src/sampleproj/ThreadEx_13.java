package sampleproj;

// 공유객체를 만들고 공유객체를 쓴다 = 공유객체가 가지는 method를 사용

// 공유객체를 생성하기 위한 class
class Shared {
	
	// Thread가 가지는 이름을 10번 찍는 method
	// 공유객체의 공유method
	public synchronized void printName() {
		
		try {
			for(int i=0; i<10; i++) {
				Thread.sleep(1000); // 1초 쉬고 찍기
				System.out.println(Thread.currentThread().getName());
				notify(); // blocked 된 thread 먼저 꺼내고 내가 wait 들어가기........
				wait();
			}
		} catch (Exception e) {
			
		}
	}
}

class ThreadEx_13_1 implements Runnable {
	public ThreadEx_13_1() {
		
	}
	
	
	public ThreadEx_13_1(Shared shared) {
		super();
		this.shared = shared;
	}


	private Shared shared;
	
	@Override
	public void run() {
		shared.printName();
		
	}
	
}
public class ThreadEx_13 {
	
	public static void main(String[] args) {
		// 공유 객체
		Shared shared = new Shared();
		
		Thread t1 = new Thread(new ThreadEx_13_1(shared), "첫번째 쓰레드");
		Thread t2 = new Thread(new ThreadEx_13_1(shared), "두번째 쓰레드");
		
		t1.start();
		t2.start();
	}

}
