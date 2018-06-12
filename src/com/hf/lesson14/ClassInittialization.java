package com.hf.lesson14;

import java.util.Random;

public class ClassInittialization {
	public static Random ran = new Random(47);
	public static void main(String[] args) throws ClassNotFoundException {
		Class initable = Initable.class;
		System.out.println("After creating Initable ref");
		System.out.println(Initable.staticFinal);// 未调用静态块
		System.out.println(Initable2.staticNonFinal);// 先调用静态块
		
		Class initable3 = Class.forName("com.hf.lesson14.Initable3");// 调用静态块
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.staticNonFinal);
//		Class<Number> inti=int.class;
		Class<?> inti=int.class;
		Class<? extends Number> inti1=int.class;  
	}
}
class Initable{
	static final int staticFinal =47;
	static final int staticFinal2 = ClassInittialization.ran.nextInt(1000);
	static {
		System.out.println("Initialezing Initable");
	}
}

class Initable2{
	static int staticNonFinal = 147;
	static {
		System.out.println("Initializing Initable2");
	}
}

class Initable3{
	static int staticNonFinal = 74;
	static {
		System.out.println("Initializing Initable3");
	}
}