//ThreadEx_07 로직으로 바꾸는 중

package sampleproj;

class ThreadEx_08_1 implements Runnable {

	// field
	volatile boolean suspended = false; // 캐시값이 아니라 실제 데이터 확인하세요
	volatile boolean stopped = false;
	
	@Override
	public void run() {
		
		while(!stopped) {
			if(!suspended) {
				System.out.println(Thread.currentThread().getName());
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
				}
			}
		}
		
	}
	
	public void suspend() { suspended = true; } // overriding 아님. 기능이 같은 method 내가 만드는 것
	public void resume() { suspended = false; } 
	public void stop() { stopped = true; }
}


public class ThreadEx_08 {

	public static void main(String[] args) {
		// Runnable 객체를 공유하면 안됨
		ThreadEx_08_1 r1 = new ThreadEx_08_1();
		ThreadEx_08_1 r2 = new ThreadEx_08_1();
		ThreadEx_08_1 r3 = new ThreadEx_08_1();
		
		Thread t1 = new Thread(r1,"*"); // run() 호출. ""안이 Thread의 이름
		Thread t2 = new Thread(r2,"**");
		Thread t3 = new Thread(r3,"***");
		
		
		// runnable 상태로
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(2000); // main thread sleep
			// 첫번째 thread를 일시정지할거에요
			r1.suspend(); // thread를 직접 제어하는 게 아니라 thread가 동작하는 방식을 바꿔주는 것
						  // Thread 가 가지고 있는 Runnable 객체의 field값을 조절해서 Runnable 객체가 가지고 있는
						  // run() method의 로직을 변화시키는 거에요.
			// while 통과 if 넘어가고 다시 while로 if 넘어가고 while ..... (busy waiting)
			Thread.sleep(2000);
			r2.suspend();
			Thread.sleep(2000);
			r1.resume(); // 이거 왜 안나와요? 로직은 문제 없는 것 같은데 -> volatile
			r1.stop();
			r2.stop();
			Thread.sleep(2000);
			r3.stop();
			
		} catch (Exception e) {
			
		}
		
		
		
	}
}
