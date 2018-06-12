package com.hf.lesson10.a3;

import com.hf.lesson10.a1.A1;
import com.hf.lesson10.a2.A2;

public class A4 extends A2{
	public A1 a3() {
		return new A3();// 要访问父类的受保护的内部类，这个内部类构造方法必须为public
	}
}
