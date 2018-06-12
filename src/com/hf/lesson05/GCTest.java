package com.hf.lesson05;

/**
 * java 垃圾回收机制
 * @author ciker
 * @desc   
 *
 */
public class GCTest {
	public static void main(String[] args) {
		Num num1=new Num();
		Num num2=new Num();
		num1.object=num2;
		num2.object=num1;
		num1=null;
		num2=null;
	}
}

/**
 * 引用计数：给每一对象都包含一个计数器，当有引用连接该对象时便将计时器加1，当离开作用域或对象置为null时，便减1，直到计数器值为0时便回收，释放其占用的空间。
 * @author ciker
 * @desc   缺点：不适用于交互自引用对象组  
 *
 */
class Num{
	public  Num object=new Num();
	
}
