package com.hf.lesson08;

public class MoreTest extends Base{

	@Override
	void d() {
		System.out.println("more d() ");
	}
	public static void main(String[] args) {
		Base base=new MoreTest();
		base.f();
	}
}
class Base{
	void f() {
		System.out.println("f()..b");
		d();
		System.out.println("f()..a");
		
	}
	void d() {
		System.out.println("d()");
	}
}