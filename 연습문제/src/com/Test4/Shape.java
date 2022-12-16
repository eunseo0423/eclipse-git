package com.Test4;

public abstract class Shape {
	
	// 생성자
	public Shape() {
	}
	
	
	
	public Shape(Point point) {
		super();
		this.point = point;
	}



	// field
	protected Point point;


	// getter & setter 
	public Point getPoint() {
		return point;
	}



	public void setPoint(Point point) {
		this.point = point;
	}
	
	
	// method
	public abstract double getArea(); // 넓이
	
	public abstract double getCircumference(); // 둘레
}
