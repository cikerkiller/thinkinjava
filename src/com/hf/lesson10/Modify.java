package com.hf.lesson10;

public class Modify {
	private int i=11;
	private int f() {return i;}
	
	class Inner{
		private int aa=3;
		int ff() {
			i++;
			return f();
		}
	}
	
	public void fff() {
		Inner inner=new Inner();
		System.out.println(inner.ff());
	}
	public static void main(String[] args) {
		Modify modify=new Modify();
		modify.fff();
		int a=modify.new Inner().aa;
		System.out.println(a);
	}
}
