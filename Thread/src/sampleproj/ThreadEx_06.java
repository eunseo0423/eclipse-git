package sampleproj;

import javax.swing.JOptionPane;

// Thread class 상속받아서
class ThreadEx_06_1 extends Thread {
	
	
	@Override
	public void run() {
		// 실제로 하는 일
		int i = 10; // 지역변수
		
		while(i != 0 && !isInterrupted()) { // 이 조건이 참일동안 계속 돌아감 ()안의 조건이 참일 동안. 반복문
			// i가 0이 아닐동안 돌아감
			// 'this.' 생략. 여기서 this는 Thread. 여기서는 t
			
			System.out.println(i--); // 현재 i의 값 출력하고 i값을 감소시킴
			
			// busy-waiting 하는 일도 없이 시간 끌 때. CPU(core)타임 소모. 좋은 코드는 아님. CPU 낭비 ㅜㅜ 
			for(long k=0; k<1000000000000000000L; k++); // 천천히 실행중
			
		}
		System.out.println("카운트가 종료되었습니다!"); 
	}		
}

public class ThreadEx_06 {

	public static void main(String[] args) {
		Thread t = new ThreadEx_06_1(); // thread instance 생성
		
		t.start(); // thread를 Runnable 상태로 전이시킴
				   // 바로 실행은 안되지만 언젠가는 Thread Scheduler에 의해 실행이 될거에요
		
		// 약간의 delay 구지
		String data = JOptionPane.showInputDialog("값을 입력하세요!"); // showInputDialog는 static
		// 잠시 hold 시키기 위한 기능
		
		System.out.println(data);
		
		t.interrupt(); // 방해한다 = thread의 (눈에 보이지 않는)내부 변수의 값을 살짝 바꿔주는 것(상태값 변경). thread가 멈추지 않음. 
					   // thread를 interrupt 시켰어요. 
					   // thread가 중지, 일시정지되는 일은 발생하지 않음
		               // while 조건문 수정해서 stop과 같은 효과를 낼 수 있게 만들음
					   // 로직으로 thread의 행동을 제어해요
		
//		t.suspend(); // 일시정지
//		t.stop(); // 중지
		
	}
}
