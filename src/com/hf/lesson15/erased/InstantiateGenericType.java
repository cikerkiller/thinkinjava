package com.hf.lesson15.erased;

import java.lang.reflect.Constructor;

/**
 * 类型实例
 * 
 * @author ciker
 * @desc
 *
 */
public class InstantiateGenericType {
	public static void main(String[] args) {
		ClassAsFactory<Employee> ca = new ClassAsFactory<>(Employee.class, null);
		ClassAsFactory<Integer> ca1 = new ClassAsFactory<>(Integer.class, new String[] { "1" }, String.class);// 运行异常，Integer没有默认构造器
	}
}

class ClassAsFactory<T> {
	T x;

	// public ClassAsFactory(Class<T> kind) {
	// try {
	// x = kind.newInstance();
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }

	public ClassAsFactory(Class<T> kind, Object[] initargs, Class<?>... parameterTypes) {
		try {
			if (initargs == null) {
				x = kind.newInstance();

			} else {
				Constructor<T> c = kind.getConstructor(parameterTypes);
				x = c.newInstance(initargs);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

class Employee {
}
