package com.hf.lesson02;

import java.util.Random;

/**
 * 测试初始化参数类
 * @author ciker
 * @desc   
 *
 */
public class InitParam {
	private int age;
	private char sex;
	public static int a;
	public static void main(String[] args) {
		InitParam initParam=new InitParam();
		InitParam initParam1=new InitParam();
		initParam.show();
		System.out.println(initParam.a);
		System.out.println(initParam1.a);
		initParam1.show();
		System.out.println(initParam.a);
		System.out.println(initParam1.a);
		Random random=new Random();
		System.out.println(random.nextInt(50));
		
	}
	public void init() {
		System.out.println("age=="+age);
		System.out.println("sex=="+sex+" ,sex is null ? ");
		System.out.println(sex=='\u0000');
		
		
	}
	public  void show() {
		a++;
	}
}
