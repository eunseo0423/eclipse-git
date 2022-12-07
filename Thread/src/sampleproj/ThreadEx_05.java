package sampleproj;

class ThreadEx_05_1 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("-");
		}
		System.out.println("<< Thread 1 종료 >> ");
	}
}

class ThreadEx_05_2 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("|");
		}
		System.out.println("<< Thread 2 종료 >> ");
	}
}

public class ThreadEx_05 {

	public static void main(String[] args) {
		Thread t1 = new ThreadEx_05_1();
		Thread t2 = new ThreadEx_05_2();
		
		t1.start();
		t2.start();
		
//		try {
////			t1.sleep(2000); // 이거는 t1 인스턴스에 대한 sleep()이 아니다. sleep()은 static이기 때문이다. t1.sleep() 이라고 적어도, Thread.sleep()이 호출되는 것.
//			Thread.sleep(2000); // 이렇게 작성하는 게 올바른 작성법이다.
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("<< main thread 종료 >>");
	}
}
