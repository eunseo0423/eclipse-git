package finaltest;

// java finaltext.Subclass => 실행
// 제일 먼저 class에 대한 정보가 method area에 올라가야 해요
// method area에 class의 정보가 잘 저장되면 그 다음에는 프로그램의 시작 포인트에서 
// 프로그램을 시작 => main()

public class SubClass extends SuperClass {
	
	// constructor
	public SubClass() {
		super(100); // 상위 클래스의 instance생성부분
		
		// 인스턴스가 메모리에 공간을 확보
		
		// 이 아래가 객체 초기화 작업
		staticCall("8번 문장입니다.");
		super.myFunc();
	}
	
	// field
	int c = staticCall("6번 문장입니다.");
	static int d = staticCall("7번 문장입니다.");
	
	// method
	// method overriding 할거에요.

	@Override // annotation (overriding하고 있다)
	public void myFunc() {
		System.out.println("9번 문장입니다.");
	}
	
	public static void main(String[] args) {
		System.out.println("10번 문장입니다.");
		SuperClass obj = new SubClass();
		obj.myFunc();
	}