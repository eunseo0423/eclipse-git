package com.test;
import java.lang.*; //기본으로 삽입되는 것(내가 안쓰면 기본으로 삽입.)

public class Student { // extends Object도 자동으로 삽입
	
	//constructor
	public Student() {
		// 인자가 없는 생성자 . default 생성자
		
	}
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	//field
	private String name;
	private int age;
	
	
	//method
	@Override
	public boolean equals(Object obj) {
		// equals method의 기능을 재정의
		// instance의 이름과 나이가 같으면 같다라고 새롭게 만들거에요.
		
		Student target = (Student)obj; // 타입 변경.
		boolean result = false;
		if((this.name.equals(target.name) &&
		   (this.age == target.age)) {
				result = true;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return this.name + ", " + this.age;
	}

	//getter&setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	

}
