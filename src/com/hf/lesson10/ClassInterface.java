package com.hf.lesson10;

/**
 * 接口内部类：在接口中的内部类自动为public static 
 * @author ciker
 * @desc   
 *
 */
public interface ClassInterface {
	void show();
	class InnerClass implements ClassInterface{

		@Override
		public void show() {
			System.out.println("show....");
		}
		public static void main(String[] args) {
			ClassInterface classInterface=new InnerClass();
			classInterface.show();
		}
	}
}
