package com.hf.lesson10;

import com.hf.lesson10.WithInner.Inner;

/**
 * 内部类的继承
 * @author ciker
 * @desc   
 *
 */
public class InheritInner extends Inner {
	public InheritInner(WithInner wi,String s) {
		wi.super(s);// 内部类构造器必须连接到指向其外围类的引用 即内部类的对象的创建必须由其外部类的对象来创建
	}
	public static void main(String[] args) {
		WithInner inner=new WithInner();
		InheritInner inheritInner=new InheritInner(inner,"");
	}
}
class WithInner{
	public WithInner() {System.out.println("withinner...");}
	class Inner{Inner(String s){System.out.println("Inner....");}}
}