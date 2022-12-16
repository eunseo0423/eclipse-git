package com.Test4;

public class Circle extends Shape implements Movable {

	// 생성자
	public Circle() {
		
	}
	
	
	public Circle(int radius, int x, int y) {
		super(new Point(x,y)); // Shape 한테 Point 객체 전달
		this.radius = radius;
	}


	// field
	private int radius;
	
	// method
	
	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	@Override
	public double getArea() {
		return Math.round(Math.pow(radius, 2) * Math.PI); 
		// 자바가 우리에게 제공해주는 수학과 관련된 Class, Math
		// 상수는 기본적으로 대문자
		// pow = n제곱
		// round 라는 method가 소숫점 첫째자리에서 반올림해줌
	}

	@Override
	public double getCircumference() {
		return Math.round(2 * radius * Math.PI);
	}
	
	@Override
	public void move(int x, int y) {
		// x와 y의 좌표값을 변경하는 것
		Point p = getPoint();
		p.setX(p.getX() + x + 1);
		p.setY(p.getY() + y + 1);
		setPoint(p);
		
	}
	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName()+ "    "
				+ radius + "    " 
				+ getPoint().getX() + "   "
				+ getPoint().getY() + "   "
				+ getArea() + "   "
				+ getCircumference(); 
 	}
	
	
}
