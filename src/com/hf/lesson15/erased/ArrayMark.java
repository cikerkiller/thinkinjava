package com.hf.lesson15.erased;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMark<T> {
	private Class<T> kind;

	public ArrayMark(Class<T> kind) {
		super();
		this.kind = kind;
	}

	@SuppressWarnings("unchecked")
	T[] create(int size) {
		return (T[]) Array.newInstance(kind, size);
	}

	public static void main(String[] args) {
		ArrayMark<String> strmark = new ArrayMark<>(String.class);
		String[] strings = strmark.create(9);
		System.out.println(Arrays.asList(strings));
	}
}
