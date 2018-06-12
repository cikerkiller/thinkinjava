package com.hf.lesson15;

/**
 * 泛型方法
 * @author ciker
 * @desc   
 *
 */
public class GennericMethods {
	public <T> void f(T v) {System.out.println(v.getClass().getSimpleName());}
	public <T,U> void f(T v,U u,GennericMethods e) {
		System.out.println(v.getClass().getName()+" , "+u.getClass().getName()+" , "+e.getClass().getName());
	}
	public static void main(String[] args) {
		GennericMethods gm = new GennericMethods();
		gm.f("ssss");
		gm.f(1);
		gm.f(1.0d);
		gm.f(1.0f);
		gm.f(1l);
		
		gm.f(1, "", gm);
	}
}
