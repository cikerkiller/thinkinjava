package com.hf.lesson01;

public class Test extends AbstractTest{
	public  String test="Test";

	@Override
	public void test1() {
		System.out.println("this is a test -> test1");
		test2();
	}
	public static void main(String[] args) {
		AbstractTest abstractTest=new Test();
		abstractTest.test1();
		System.out.println(abstractTest.test);
//		abstractTest.test2();// 基类不能访问导出类新增的方法
		
		
		Test t1=new Test(); 
		t1.test="t1";  
		Test t2=t1;
		t2.test="t2";
		System.out.println(t1.test);
		
	}
	int a ;
	public void test2() {
		System.out.println(a);
		System.out.println("this is a test2:"+test);
	}
}
