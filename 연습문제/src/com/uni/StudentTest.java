package com.uni;

public class StudentTest {

	// method
	public static void main(String[] args) {
		
		// 배열을 만드세요 
		Student arrays[] = new Student[3]; // Student가 class, data type
		
		// 데이터 입력
		arrays[0] = new Student("홍길동",15,171,81,"201101","영문");
		arrays[1] = new Student("한사람",13,183,72,"201102","건축");
		arrays[2] = new Student("임걱정",15,175,65,"201103","무용");
		
		// 출력
		// 배열에 있는 객체를 모두 출력한다. for문 이용
		// 확장 for문
		// 집합자료형 : 배열, List, Map. 배열과 같이 여러개의 데이터를 한꺼번에 저장하고 있는 객체
		for(Student s : arrays) { // ( : 배열이름)
			System.out.println(s.printInformation());
		}
		
			
	}
}
