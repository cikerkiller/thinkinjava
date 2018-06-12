package com.hf.lesson15;

import java.lang.reflect.Method;

public class BaseGeneratorDome {
	public static void main(String[] args) {
		Generator<CountedObject> gen = BaseGenerator.create(CountedObject.class);
		cycle(gen,"next", 5,new Object[] {});
		gen=new BaseGenerator<>(CountedObject.class);
		cycle(gen,"next", 5,new Object[] {});
	}
	
	/**
	 * 有参循环
	 * @param obj
	 * @param method
	 * @param n
	 * @param objs
	 * @param parameterTypes
	 */
	public static void cycle(Object obj,String method,int n,Object[] objs,Class<?>... parameterTypes) {
		try {
			Method method2 = obj.getClass().getMethod(method, parameterTypes);
			for(int i=0;i<n;i++) {
				System.out.println(method2.invoke(obj,objs));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public static void cycle(Object obj,String method,int n) {
		try {
			Method method2 = obj.getClass().getMethod(method);
			for(int i=0;i<n;i++) {
				System.out.println(method2.invoke(obj));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
