package com.hf.lesson07;

public class C extends A{
	C(int i) {
		super(i);
	}
	C(){
		super(0);	
	}
	void f() {
		System.out.println("c f");
	}
	String f(String a) {
		return a;
	}
	B b=new B();
	private final void ff() {}
	public static void main(String[] args) {
		C c=new C();//向上转型会丢失方法
		//B bb=c.b;
		c.ff();
		
	}
}
class A{
	final int b;
	private final void ff() {
	}
	A(int i){
		b=0;
		System.out.println(" A() ");
	}
	void f() {
		System.out.println("A f");
	}
	int f(int a) {
		return a;
	}
}

class B{
	B(){
		System.out.println(" B() ");
	}
}