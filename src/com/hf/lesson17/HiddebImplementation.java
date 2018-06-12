package com.hf.lesson17;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.hf.lesson14.A;
import com.hf.lesson14.HiddenC;
/**
 * 通过反射可以访问类所有的信息，包括私有的
 * @author ciker
 * @desc   
 *
 */
public class HiddebImplementation {
	public static void main(String[] args) {
		A a = HiddenC.makeA();
		a.f();
		callHiddenMethod(a, "g");
		callHiddenMethod(a, "gg");
		callHiddenMethod(a, "ggg");
		callHiddenMethod(a, "gggg");
	}
	
	public static void callHiddenMethod(Object o,String methodName) {
		try {
			Method method = o.getClass().getDeclaredMethod(methodName);
			try {
				method.setAccessible(true);// 设置成可访问
				method.invoke(o);
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
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
