package com.hf.lesson05;

public class ExplicitStatic {
	public static void main(String[] args) {
		Cups.cup1.f(99);
	}
	static Cups cups=new Cups();
}

class Cup{
	Cup(int marker){
		System.out.println("Cup("+marker+")");
	}
	void f(int marker) {
		System.out.println("f("+marker+")");
	}
}

class Cups{
	static Cup cup1;
	static Cup cup2;
	static {
		cup1=new Cup(1);
		cup2=new Cup(2);
	}
	Cups(){
		System.out.println("Cups()");
	}
}