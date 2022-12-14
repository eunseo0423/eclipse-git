package com.computer;

public class Laptop extends Computer implements Graphic {

	// 당연히 추상 method를 overriding해야
	// 우리 class가 추상 class가 되지 않음
	
	// 생성자
	public Laptop() {
		
	}
	
	
	// source > operate from Superclass
	public Laptop(String name, int cpu, int memory, int battery) {
		super(name, cpu, memory, battery);
		// TODO Auto-generated constructor stub
	}


	// class명 빨간 밑줄에 마우스 댄 다음 override
	@Override
	public double rendering(int size) {
		return (double)size / (getCpu() * getMemory())*20;
		// size가 int인데 데이터 타입이 같아야하니까 double로 변환
	}

	@Override
	public void operate(int time) {
		// 입력으로 들어온 시간 10 당 1씩 배터리가 감소 
		int result = time / 10;
		// 프로그램에서 연산(+,-,*,/)은 일단 같은 데이터 타입끼리 발생.
		setBattery(getBattery() - result); 
		
	}

	
	
	
}
