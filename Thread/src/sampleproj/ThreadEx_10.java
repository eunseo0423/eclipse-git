package sampleproj;

class ThreadEx_10_1 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("-");
		}
	}
}

class ThreadEx_10_2 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("|");
		}
	}
}

public class ThreadEx_10 {

	public static void main(String[] args) {
		long startTime = 0; // 시간체크하기위한 변수
		
		Thread t1 = new ThreadEx_10_1();
		Thread t2 = new ThreadEx_10_2();
		
		t1.start();
		t2.start();   
		
		startTime = System.currentTimeMillis(); // 현재 시간을 millisecond 단위로 리턴. 숫자로 현재시간을 표현
		
		try {
			t1.join(); // main thread 멈춤. t1이 끝날 때까지. 그래서 순서를 잡을 수 있음
			t2.join(); // t2가 끝날때까지 main이 멈춤
			// t1,t2는 이 안에서 또 경쟁
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// main이 마지막으로 진행
		System.out.println("소요시간 : " 
		+ (System.currentTimeMillis() - startTime) ); // startTime 부터 sysout까지 얼마만큼의 시간이 걸렸는지 계산(시간체크할 때 많이 사용)
		
	}
}
