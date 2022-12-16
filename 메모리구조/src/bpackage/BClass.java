package bpackage; // package 구문을 제일 먼저

import apackage.*; // import 구문은 여러 개 나올 수 있음

//public class BClass {
class BClass {

	// 1.constructor
//		public BClass() {
			
//		}
	    BClass() {
	    	
	    }
		
		// 2. field 
	    // 필드는 특별한 목적을 두지 않는 이상 private(외부에서 값을 변경하는 것 방지하기 위해서)   
//		public int bField;
		private int bField; //값을 바꾸거나 알아내는 두가지 작업을 우리 클래스 내에서만 허용하는 것
		// bField라는 pivate field는 우리 class 내에서만 조회와 변경이 가능하도록 처리해야 해요.
		// 특수한 목적의 method를 이용해야 함.
		// 하나는 private field의 값을 조회하는 목적을 가진 method
		// 다른 하나는 private field의 값을 변경하는 목적을 가진 method
		// getter(조회) & setter(변경) - 쌍을 이뤄서 다님
		
		// getter부터 만들어 보아요~ : 값을 알아내는 getter
		// 값을 알아내는 것은 다른 클래스에서 알아내는 목적으로 사용하는 것이기 때문에 public으로
		// 외부에서 getter 사용할 수 있게
		// access modifier는 public
//		public int getBField() {  // bField의 타입을 적어줌, getter 이름을 명시하는 규칙이 있음
//			return this.bField;   // 필드의 이름그대로 가져와서 필드의 첫글자 대문자로 바꿔줌
//		}
//		
//		public void setBField(int bField) { // 바꾸고 끝낼거라서 리턴값이 없음
//			this.bField = bField; 
//		}
		
		public int getbField() {
			return bField;
		}

		public void setbField(int bField) {
			this.bField = bField;
		} 								// source 메뉴에서 자동으로 만든 getter & setter
		
		
		
		// 3. method
		// class안의 method는 대부분 field와 연관된 기능들을 하는 것들
//		public void bMethod() {
//			System.out.println("하이.");
//		}

		void bMethod() {
			System.out.println("하이.");
		}

		
		
//		public static void main(String[] args) {
			// AClass의 instance를 만들어 보아요~~
//			apackage.AClass a = new apackage.AClass();
//			AClass a = new AClass();
//			a.aField = 100;
//		}
}
