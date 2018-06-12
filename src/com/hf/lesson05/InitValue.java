package com.hf.lesson05;

/**
 * 初始化
 * @author ciker
 * @desc   
 *
 */
public class InitValue {
	Window window;
	InitValue(){
		window=new Window();
	}
	InitValue(Window win){
//		window=win;
	}
	
	public static void main(String[] args) {
		int abc = Window.abc;
		System.out.println(abc);
	}
}
class Window{
	static int abc;
	static String str;
	static {
		System.out.println("this is a static "+str);
	}
	Window(){
		System.out.println("window");
	}
}
