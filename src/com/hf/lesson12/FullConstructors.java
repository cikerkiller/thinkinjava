package com.hf.lesson12;

public class FullConstructors {
	public static void f() throws MyException1{
		System.out.println("throwing myexception from f()");
		throw new MyException1();
	}
	public static void g() throws MyException1{
		System.out.println("throwing myexception from g()");
		throw new MyException1("Originated in g()");
	}
	
	public static void main(String[] args) {
		try {
			f();
		} catch (MyException1 e) {
			e.printStackTrace(System.out);
		}
		
		try {
			g();
		} catch (MyException1 e) {
			e.printStackTrace();
//			e.printStackTrace(System.out);
		}
	}
}
class MyException1 extends Exception{
	public MyException1() {}
	public MyException1(String msg) {super(msg);}
}