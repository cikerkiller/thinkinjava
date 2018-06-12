package com.hf.lesson15;

public class AarrayOfGeneric {
	static final int size = 100;
	static Generic<Integer>[] gia;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		gia = (Generic<Integer>[]) new Generic[size];
		System.out.println(gia.getClass().getSimpleName());
		gia[0] = new Generic<Integer>();
	}
}

class Generic<T> {
}