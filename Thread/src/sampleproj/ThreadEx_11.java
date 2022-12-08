package sampleproj;

class ThreadEx_11_1 extends Thread {
	
	// 상수 필드 하나를 선언할거에요
	// 상수 필드는 관용적으로 대문자를 사용하고 snake case 사용
	final static int MAX_MEMORY = 1000;
	int usedMemory = 0; // 일반 필드
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000); // 10초간 자요 = 10초마다 깨어나서 
			} catch (Exception e) {
			}
			gc(); // memory 청소해서 memory 용량을 다시 확보하는 method
			System.out.println("남은 메모리량 : " + freeMemory());
		}
	}
	
	public void gc() {
		usedMemory = usedMemory - 300;
		if(usedMemory < 0) {
			usedMemory = 0;
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
		
	}
}
