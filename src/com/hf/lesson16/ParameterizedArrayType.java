package com.hf.lesson16;

import java.util.Arrays;

public class ParameterizedArrayType {
	public static void main(String[] args) {
		Integer[] a = {1,2,3,4,5,6};
		Integer[] f = new ClassParameter<Integer>().f(a);
		System.out.println(Arrays.toString(f));
		
		Integer[] f2 = MethodParamter.f(a);
		System.out.println(Arrays.toString(f2));
		
	}
}
// 参数化类
class ClassParameter<T>{
	public T[] f(T[] arg) {
		return arg;
	}
}
// 参数化方法
class MethodParamter{
	public static <T> T[] f(T[] arg){
		return arg;
	}
}
