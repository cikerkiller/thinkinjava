package com.hf.lesson10;

public class DotThis {
	void f() {System.out.println("dotthis.f()");}
	
	public class Inner{
		public DotThis outer1() {
			return new DotThis();
		}
		public DotThis outer2() {
			return DotThis.this;
		}
	}
	public Inner inner() {return new Inner();}
	public static void main(String[] args) {
		DotThis dotThis=new DotThis();
		Inner inner=dotThis.inner();
		inner.outer2().f();
		com.hf.lesson10.Outer.Inner inner2=new Outer("2132"). new Inner();
		System.out.println(inner2);
	}
	
	
}
