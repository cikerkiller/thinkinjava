package com.hf.lesson05;

public class MyInit {
	static String str;
	static {
		System.out.println("str=="+str);
		str="static {}";
		System.out.println("str=="+str);
	}
	{
		System.out.println("str=="+str);
		str="{}";
		System.out.println("str=="+str);
	}
	String abc="abc";
	{
		System.out.println("abc=="+abc);
		abc="abc1";
		System.out.println("abc1=="+abc);
	}
	static String abc1="abc";
	static {
		System.out.println("ab1c=="+abc1);
		abc1="abc2";
		System.out.println("abc2=="+abc1);
	}
	public MyInit() {
		System.out.println("MyInit()");
	}
	public static void s() {
		System.out.println("s()");
	}
	public static void main(String[] args) {
//		MyInit init=new MyInit();
		Integer[] aa= {1,2,};
		System.out.println(aa.length);
		for(Integer i:aa) {
			System.out.println(i);
		}
	}
}
