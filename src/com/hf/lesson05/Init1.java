package com.hf.lesson05;

public class Init1 {
	public Init1(String str) {
		System.out.println("str="+str);
	}
	public Init1(Sub...str) {
		System.out.println("Sub="+str);
	}
	public static void main(String...args) {
		Init1[] inits=new Init1[] {new Init1(new Subz[] {new Subz()})};
		print(1,2,1,1,1);
		print(new Integer[]{1});
	}
	
	public static void print(Object...objects) {
		for(Object o:objects) {
			System.out.print(o+" ");
		}
		System.out.println("Object");
	}
	public static void print(Integer...objects) {
		for(Object o:objects) {
			System.out.print(o+" ");
		}
		System.out.println("Integer");
	}
}

class Sub{}
class Subz extends Sub{}

