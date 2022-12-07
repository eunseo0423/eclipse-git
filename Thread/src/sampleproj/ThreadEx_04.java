package sampleproj;

public class ThreadEx_04 implements Runnable {

	//field
	static boolean autoSave = false; // static이기 때문에, 인스턴스 생성 안 해도, 클래스 이름만으로 이 필드에 액세스 가능하다.
	
	public static void main(String[] args) {
		// 쓰레드 하나 만들자
		Thread t = new Thread(new ThreadEx_04()); // 우리 클래스의 인스턴스. runnable을 구현한 객체고, 이거를 start 하면, run() 이 실행된다.
		t.setDaemon(true); // t는 자기를 만든 쓰레드의 보조 쓰레드가 된다. 즉, main 쓰레드의 보조 쓰레드가 된다.
		
		t.start(); // t 쓰레드 시작
		
		for (int i = 0; i < 10; i++) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (i == 4) {
				// 5초가 지나면 자동저장된다.
				autoSave = true;
			}
		}
	}
	
	@Override
	public void run() {

		int tt = 0;
		while (true) {
			// 무한루프를 돌지만, 어차피 상위 쓰레드가 죽으면 같이 죽는다.
			// Thread를 일정 시간동안 재울 거야.
//			Thread.sleep(3000); // 1/1000 단위(ms). 즉, 3초 대기. sleep은 static이다. 클래스 이름으로 호출할 수 있잖아?
			
			try {
				Thread.sleep(1000); // sleep()은 현재 실행되는 쓰레드를 재울 때 사용한다.
				tt++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (autoSave == true) {
				System.out.println("자동저장되었었요!" + tt);
			}
		}
	}
}
