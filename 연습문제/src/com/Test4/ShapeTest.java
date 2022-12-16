package com.Test4;

import java.util.ArrayList;

public class ShapeTest {
	
	public static void main(String[] args) {
		
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(new Rectangle(4,7,5));
		list.add(new Rectangle(5,4,6));
		list.add(new Circle(6,6,7));
		list.add(new Circle(7,8,3));
		
		for(Shape s : list) {
			System.out.println(s);
		}
		
//		for(Shape s : list) {
//			s.move(10,10); // 상위 타입인 Shape로 잡았는데 Shape에는 move가 없음
//		}
		
		for(Shape s : list) {
			((Movable)s).move(10, 10); //일단 바꾼 다음에 move
//			(Movable)s.move(10,10) 은 Movable로 바꾸는 게 마지막
		}
		
		for(Shape s : list) {
			System.out.println(s);
		}
	}
}
