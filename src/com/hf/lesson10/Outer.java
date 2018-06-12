package com.hf.lesson10;

public class Outer {
	private String s;
	public Outer(String s) {
		this.s=s;
	}
	public Inner inner() {
		return new Inner();
	}
	class Inner{

		@Override
		public String toString() {
			return s;
		}
		
	}
	public static void main(String[] args) {
		Inner inner=new Outer("sss").new Inner();
		System.out.println(inner);
	}
}
