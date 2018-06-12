package com.hf.lesson14;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ModifyingPrivateFields {
	public static void main(String[] args) throws Exception {
		WithPrivateFinalField w =new WithPrivateFinalField();
		Field[] fields = w.getClass().getFields();// 获得所有公共字段
		System.out.println(Arrays.asList(fields));
		Field field = w.getClass().getDeclaredField("i");
		field.setAccessible(true);
		System.out.println(field.get(w));
		field.set(w, 2);
		System.out.println(field.get(w));
		Method method = w.getClass().getDeclaredMethod("toString");
		Object invoke = method.invoke(w);
		System.out.println(invoke);
	}
}
class WithPrivateFinalField{
	private int i= 1;
	private final int a = 11;// 遭遇修改是安全的，运行时系统接受修改尝试，但是实际上不会发生任何修改
	
	private String s = "I love you";
	@Override
	public String toString() {
		return "WithPrivateFinalField [i=" + i + ", a=" + a + ", s=" + s + "]";
	}
}