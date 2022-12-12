package sample;

public class ExampleTest {

	public static void main(String[] args) {
		// 우리 프로그램을 실행시키리면 
		// javac compiler 로 ExampleTest.java compile
		// 결과로 bytecode인 ExampleTest.class 파일이 생성돼요
		// java ExampleTest 엔터눌러서 실행
		
		// 프로그램 실행할때 java ExampleTest 10 20 (입력인자) 넘겨줌. (스트링 배열)
		System.out.println(args[0] + ", " + args[1]);
	}
}
