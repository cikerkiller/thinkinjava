package com.hf.lesson14;

import com.hf.lesson17.HiddebImplementation;

/**
 * 反射调用匿名类的方法
 * @author ciker
 * @desc   
 *
 */
public class AnonymousImplementation {
	public static void main(String[] args) {
		A a = AnonymousA.makeA();
		a.f();
		System.out.println(a.getClass().getName());
		HiddebImplementation.callHiddenMethod(a, "g");
		HiddebImplementation.callHiddenMethod(a, "gg");
		HiddebImplementation.callHiddenMethod(a, "ggg");
	}
}
class AnonymousA{
	public static A makeA() {
		return new A() {
			@SuppressWarnings("unused")
			private void g() {System.out.println("g....");}
			@SuppressWarnings("unused")
			protected void gg() {System.out.println("gg....");}
			@SuppressWarnings("unused")
			void ggg() {System.out.println("ggg....");}
			@Override
			public void f() {
				System.out.println("f..");
			}
		};
	}
}