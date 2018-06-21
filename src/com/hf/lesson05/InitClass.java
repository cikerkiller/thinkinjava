package com.hf.lesson05;

/**
 * 类的初始化
 * @author ciker
 * @desc   
 *
 */
public class InitClass {
	static int i =0;
	public static void main(String[] args) {
		System.out.println(i);
		InitClass class1=new InitClass();
//		System.out.println(class1.str);
//		class1=new InitClass("1233");
//		System.out.println(class1.str);
//		System.out.println(class1.str1);
//		class1=new InitClass((byte)1); 
	}
	static {
		System.out.println("static");
	}
//	public InitClass() {
//		System.out.println("this..");
//	}
//	public InitClass(int a) {
//		this();//放在第一位
//		System.out.println("int");
//	}
//	public InitClass(byte a) {
//		this((int)a);//放在第一位
//		System.out.println("byte");
//		
//	}
//	public InitClass(String str) {
//		this.str=str;  
//	}
	private String str;
	private String str1="123";
	
}
