package com.hf.lesson11;

import java.util.ArrayList;

public class ApplesAndOrangesWithoutGenerics {
	//@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//		@SuppressWarnings("rawtypes")
		ArrayList<Apple> apples=new ArrayList<>();
		for(int i=0;i<3;i++) {
			apples.add(new Apple());
		}
		// compile-time error 编译器错误
		//apples.add(new Orange());
		for(int i=0;i<apples.size();i++) {
//			System.out.println(((Apple)apples.get(i)).id());
			System.out.println(apples.get(i).id());
		}
	}
}
class Apple{
	private static long counter;
	private final long id = counter++;
	public long id() {return id;}
}

class Orange{}