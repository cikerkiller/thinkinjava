package com.hf.lesson10.a2;

import com.hf.lesson10.a1.A1;

public class A2 {
	protected class A3 implements A1{
		
		public A3() {
		}
		@Override
		public void f() {
			System.out.println("A2.A3==>A1");
		}
	}
	public static void main(String[] args) {
		A3 name=new A2().new  A3();
	}
}
