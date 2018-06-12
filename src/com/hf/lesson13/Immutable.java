package com.hf.lesson13;

public class Immutable {
	public static String upcase(String s) {
		return s.toUpperCase();
	}
	public static void main(String[] args) {
		String q = "hello";
		System.out.println(q);
		String qq = upcase(q);
		System.out.println(qq);
		System.out.println(q);  
		String intern = q.intern();
		System.out.println(intern);  
		System.out.printf("Row 1 :[%d %f]\n", 23,0.2);
		System.out.format("Row 1 :[%d %f]\n", 23,0.2);
		
	}
}
