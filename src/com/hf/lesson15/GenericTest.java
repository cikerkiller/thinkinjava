package com.hf.lesson15;

public class GenericTest {
	public static final int size = 10;

	public static void main(String[] args) {
		FixedSizeStack<String> fixed = new FixedSizeStack<String>(size);
		for (String s : "A B C D E F G H".split(" ")) {
			fixed.put(s);
		}
		// 数组的长度要10，不然会抛异常，这个第一次忽略了
		for (int i = 0; i < size; i++) {
			System.out.println(fixed.pop());
		}
	}
}

class FixedSizeStack<T> {
	private int index = 0;
	private Object[] storage;

	public FixedSizeStack(int size) {
		storage = new Object[size];
	}

	public void put(T t) {
		storage[index++] = t;
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		return (T) storage[--index];
	}
}