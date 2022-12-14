package sampleproj;

class ThreadEx_07_1 implements Runnable {

	@Override
	public void run() {


		while(true) {
			// 현재 수행중인 Thread를 찾아 Thread의 이름을 출력해요
			System.out.println(Thread.currentThread().getName());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
		}
	}
	
}

public class ThreadEx_07 {

	public static void main(String[] args) {
		// Runnable interface를 구현한 class instance를 생성 
		Runnable r = new ThreadEx_07_1();
		
		Thread t1 = new Thread(r,"*"); // run() 호출. ""안이 Thread의 이름
		Thread t2 = new Thread(r,"**");
		Thread t3 = new Thread(r,"***");
		
		
		// runnable 상태로
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(2000); // main thread 잠들어용
			t1.suspend(); // t1 일시중지
			Thread.sleep(2000); // main 재우기  
			t2.suspend();
			Thread.sleep(2000);
			t1.resume(); // 1 살아남. 1,3 출력
			Thread.sleep(2000);
			t1.stop();
			t2.stop();
			Thread.sleep(2000);
			t3.stop();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
