package com.hf.lesson15;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Apply {
	public static <T , S extends Iterable<? extends T>> void apply(S seq, Method f, Object...args) {
		try {
			for(T t : seq) {
				f.invoke(t, args);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public static void main(String[] args) throws Exception{
		List<Shape> shapes = new ArrayList<>();
		for(int i=0;i<10;i++) {
			shapes.add(new Shape());
		}
		
//		Apply.apply(shapes, Shape.class.getMethod("rotate"));
//		Apply.apply(shapes, Shape.class.getMethod("resize",int.class),5);
//		
//		
//		
//		List<Square> squares = new ArrayList<>();
//		for(int i=0;i<10;i++) {
//			squares.add(new Square());
//		}
//		Apply.apply(squares, Square.class.getMethod("rotate"));
//		Apply.apply(squares, Square.class.getMethod("resize",int.class),6);
//		
		
		Apply.apply(new FilledList<Shape>(Shape.class, 6), Shape.class.getMethod("rotate"));
		Apply.apply(new FilledList<Shape>(Shape.class, 6), Shape.class.getMethod("resize",int.class),6);
		
		
		
	}
}
class Shape{
	public void rotate() {
		System.out.println(this+" rotate ");
	}
	
	public void resize(int newsize) {
		System.out.println(this+" resize "+newsize);
	}
}

class Square extends Shape{}

class FilledList<T> extends ArrayList<T>{
	public FilledList(Class<? extends T> type,int size) {
		try {
			for(int i=0;i<size;i++) {
				add(type.newInstance());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
