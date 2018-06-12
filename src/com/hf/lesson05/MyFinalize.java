package com.hf.lesson05;

public class MyFinalize {

	@Override
	protected void finalize() throws Throwable {
		System.out.println("this is a finalize");
		super.finalize();
	}
	
	public void show() {
		System.out.println("this is a show");
	}
	public static void main(String[] args) {
		MyFinalize finalize=new MyFinalize();
//		finalize.show();
		finalize=new MyFinalize();// finalize重新指向一个新的地址，所有下一步调用gc会生效
		System.gc();
	}
}
