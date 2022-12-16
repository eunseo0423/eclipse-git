package bpackage;

public class CClass {

	// constructor
	
	// field
	
	// method
	
	public static void main(String[] args) {
		BClass b = new BClass();
//		b.bField = 100; // 무조건 접근이 안되게 하는게 아니라 
//						// 필드에 대한 (오류로 인한) 직접적인 제어를(접근을) 막는 것
						// 접근 자체가 안되면 어떻게 값을 바꾸어야 하나요??
						// 이 목적을 위한 method가 따로 있음
						// 필드를 직접적으로 acess하는 건 정말 특별한 경우말곤 없음
		b.setbField(100); // setter를 이용해서 값을 바꿔야 함 
	}
}
