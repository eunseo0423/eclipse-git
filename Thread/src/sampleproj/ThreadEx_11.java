package sampleproj;

// 이 Thread의 instance를 생성해서 실행하면
// 10초마다 일정량의 메모리 사용량을 감소시켜요

class ThreadEx_11_1 extends Thread {
	
	// 상수 필드 하나를 선언할거에요
	// 상수 필드는 관용적으로 대문자를 사용하고 snake case 사용
	final static int MAX_MEMORY = 1000;
	int usedMemory = 0; // 일반 필드
	
	@Override
	public void run() {
		while(true) { // 무한루프
			
			try {
				Thread.sleep(10000); // 10초간 자요 = 10초마다 깨어나서 
			} catch (InterruptedException e) {
				
			} 
			
			gc(); // memory 청소해서 memory 용량을 다시 확보하는 method
			System.out.println("남은 메모리량 : " + freeMemory());
		}
	}
	
	public void gc() {
		usedMemory = usedMemory - 300; // usedMemory -= 300; 이렇게 써야해요
		if(usedMemory < 0) {
			usedMemory = 0; // 예외상황에 대한 처리
		}
	}
	
	public int totalMemory() { // 전체 메모리량 리턴하는 메소드
		return MAX_MEMORY;
	}
	
	public int freeMemory() { // 전체 메모리에서 사용된 메모리량을 뺴서 현재 가용한 메모리량을 알아내요
		return MAX_MEMORY - usedMemory;
	}
	
}

public class ThreadEx_11 {

	public static void main(String[] args) {
		
		ThreadEx_11_1 t = new ThreadEx_11_1();
		t.setDaemon(true); // 나를 만든 Thread가 종료가 되면 같이 종료되는 Daemon Thread
		t.start();
		
		int requiredMemory = 0;
		
		for(int i=0; i<20; i++) {
			requiredMemory = ((int)(Math.random() * 10)) * 20; // 0과 1 사이의(0은 포함 1은 포함하지 않음) 난수가 나옴 *10하고 정수화시켜서 
															   // 0~9 에 20 곱해서 0, 20, 40 .. 180 이렇게 나와요 
		// 위에서 구현한 필요한 메모리량이 사용할 수 있는 메모리량보다 크거나
		// 혹은 전체 메모리의 60% 이상을 사용했을 때 GC를 깨울거에요
			if((t.freeMemory() < requiredMemory) ||
					t.freeMemory() < t.totalMemory() * 0.4) {
				t.interrupt(); // t가 gc()로 정리하는 동안 main은 기다리지 않음 그래서 - 값 출력 되는 것(Thread는 병렬로 도니까) 
							   // 그래서 main을 잠시 멈추고 t를 진행시켜야함(순차처리가 필요) -> join() 이용
				try {
					t.join(100); // while이 무한루프라서 t가 끝나지 않음 100(0.1초)만큼만 main을 멈추고 t 실행
				} catch (InterruptedException e) {
				}
			}
			
			t.usedMemory += requiredMemory; // 사용된 메모리량을 누적
			System.out.println("남은 메모리량 : " + t.freeMemory());
		}
		
	}
}
