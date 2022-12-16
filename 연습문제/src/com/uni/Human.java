package com.uni;

public class Human {
	// 생성자
	public Human() {
		// default 생성자
	}
	
	public Human(String name, int age, int height, int weight) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	// field
	private String name;
	private int age;
	private int height;
	private int weight;
	
	
	// getter&setter
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	// method
	// 정보 4개를 합쳐서 문자로 만들어라
	public String printInformation() {
		return name + " " + age + " " + height + " " + weight;
	}
}
