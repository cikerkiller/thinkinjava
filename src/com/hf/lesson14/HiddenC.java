package com.hf.lesson14;

public class HiddenC {
	public static A makeA() {return new C();}
}
class C implements A{

	@Override
	public void f() {
		System.out.println("f()");
	}
	public void g() {System.out.println("g()");}
	@SuppressWarnings("unused")
	private void gg() {System.out.println("gg()");}
	void ggg() {System.out.println("ggg()");}
	protected void gggg() {System.out.println("gggg()");}
}
