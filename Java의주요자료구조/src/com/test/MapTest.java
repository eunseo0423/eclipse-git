package com.test;

import java.util.HashMap;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		
		HashMap<String,String> map = new HashMap<String,String>(); 
		// 저장되는게 key,value 동시에 들어가야해서
		// < , >
		
		
		// Map에 데이터를 저장할 때 
		map.put("123", "Hello");
		map.put("4", "안녕!"); // 여러개의 데이터 하나의 구조에 저장. 집합자료구조
		map.put("kk", "홍길동");
		map.put("aa", "신사임당");
		map.put("aa", "신사임당"); // 열쇠는 유니크함. 이렇게 적으면 value 값이 대체됨
		
		// Map에서 데이터를 추출할 때
		System.out.println(map.get("123")); // key값을 넣음
		
		// 이 안의 key를 다 알고 있어야 하나요?
		// Map 안에 있는 모든 key를 알고 싶어요
		
		
		Set<String> s = map.keySet(); //map이 갖구있는 key값으로 Set을 만들겠다
		// Set이 상위 타입(IS-A 관계)
		for(String a : s) {
			System.out.println(a);
		}
	}
}
