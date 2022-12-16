package com.test;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		
		// ArrayList를 하나 생성해 보아요
		ArrayList<Object> a = new ArrayList<Object>();
		a.add("Hello");
		a.add(new Student("홍길동", 20)); 
		a.add(100); // Auto Boxing 사용하는 class는 Integer
		a.add(3.14); // Double class (Wrapper class) Auto Boxing
		
		// 빨간 밑줄은 코드에러
		// 노란 밑줄은 주의(원인) 경고해주는 것. 잘 살펴봐라~ 
		
		
		ArrayList<String> b = new ArrayList<String>();
		b.add("Hello");
		b.add("홍길동"); // String Pool안
		b.add(new String("소리없는 아우성!")); // Heap 영역의 객체
//		b.add(100); // Error. int니까
		
	}
}
