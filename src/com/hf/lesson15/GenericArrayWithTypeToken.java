package com.hf.lesson15;

import java.lang.reflect.Array;

/**
 * 使用类型标记Class<T>恢复擦除，使可以创建需要的实际类型的数组
 * 
 * @author ciker
 * @desc
 *
 */
public class GenericArrayWithTypeToken<T> {
	private T[] array;

	@SuppressWarnings("unchecked")
	public GenericArrayWithTypeToken(Class<T> type, int sz) {
		array = (T[]) Array.newInstance(type, sz);
	}

	public void put(int index, T item) {
		array[index] = item;
	}

	public T get(int index) {
		return array[index];
	}

	public T[] rep() {
		return array;
	}

	public static void main(String[] args) {
		GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
		Integer[] rep = gai.rep();
	}
}
