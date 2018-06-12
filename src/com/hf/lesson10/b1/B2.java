package com.hf.lesson10.b1;
/**
 * 练习11
 * @author ciker
 * @desc   
 *
 */
public class B2 {
	public B1 getB() {
		class B3 implements B1{
			@Override
			public void f() {
				System.out.println("B3.....");
			}
		}
		return new B3();
	}
	
	public static void main(String[] args) {
		B2 b2=new B2();
		//B3 b = (B3) b2.getB(); // B3被隐藏了
		B1 b=b2.getB();
		b.f();
	}
}	
