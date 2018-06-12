package com.hf.lesson14.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShowMethods {
	private static String usage = "usage:\n"
			+ "ShowMethods qualifed.class.name\n"
			+ "To show all methods in class or:\n"
			+ "ShowMthods qualified.class.name word\n"
			+ "To search for methods incolving 'word'";
	private static Pattern p = Pattern.compile("\\w+\\.");
	public static void main(String[] args) throws ClassNotFoundException {
		args=new String[] {"com.hf.lesson14.reflect.ShowMethods1","com.hf.lesson14.reflect.ShowMethods1"};
		int lines = 0;
		Class<?> c = Class.forName(args[0]);
		Method[] methods = c.getMethods();
		Constructor<?>[] constructors = c.getConstructors();
		if(args.length==1) {
			for(Method m : methods) {
				System.out.println(p.matcher(m.toString()).replaceAll(""));
			}
			for(Constructor con:constructors) {
				System.out.println(p.matcher(con.toString()).replaceAll(""));
			}
			lines=methods.length+constructors.length;
		}else {
			for(Method m:methods) {
				//System.out.println(Pattern.compile("final|native").matcher(m.toString()).replaceAll(""));
				System.out.println(m.toString());
				if(m.toString().indexOf(args[1])!=-1) {
					System.out.println("Method start:");
					System.out.println(p.matcher(m.toString()).replaceAll(""));
					lines++;
					System.out.println("Method end:");
				}
			}
			for(Constructor cc : constructors) {
				System.out.println(cc.toString());// 非public的类，默认生成的构造器不会在输出中出现
				try {
					ShowMethods1 ss=(ShowMethods1) cc.newInstance(1);
					System.out.println(ss);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(cc.toString().indexOf(args[1])!=-1) {
					System.out.println("Constructor start:");
					System.out.println(p.matcher(cc.toString()).replaceAll(""));
					lines++;
				}
			}
		}
		
	}
}
class ShowMethods1{
//	public ShowMethods1() {}
	public ShowMethods1(int a) {}
	public String toString() {return "test..........";}
}
