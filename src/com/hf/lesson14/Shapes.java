package com.hf.lesson14;

import java.util.ArrayList;
import java.util.Arrays;

public class Shapes {
	public static void main(String[] args) {
		Shape s = new Rhomoid();
		s.draw();
		// s对象是不是Circle类型
		if(s instanceof Circle) {
			((Circle)s).draw();
		}
		new Shapes().f(new ArrayList<>().getClass());
	}
	
	// 查看继承体系
	public void f(Class c) {
		Class cc=c.getSuperclass();
		if(cc!=null) {
			System.out.println(cc.getName());
			System.out.println("declaredFields: "+Arrays.asList(cc.getDeclaredFields()));
			f(cc);
		}
	}
}
abstract class Shape{
	void draw(){System.out.println(this+".draw()");}

	@Override
	public abstract String toString();// 竟然可以对父类的方法重写并加上抽象描述符
	
}

class Circle extends Shape{

	@Override
	public String toString() {
		return "Circle";
	}
	
}
class Rhomoid extends Shape{
	
	@Override
	public String toString() {
		return "Rhomoid";
	}
	
}