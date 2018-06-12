package com.hf.lesson16;

import java.lang.reflect.Array;

import com.hf.lesson15.Generator;
import com.hf.lesson17.CollectionData;

public class Generated {
	// fill an existing array
	public static <T> T[] array(T[] a, Generator<T> gen) {
		return CollectionData.list(gen, a.length).toArray(a);
	}
	
	// create a new array
	@SuppressWarnings("unchecked")
	public static <T> T[] array(Class<T> type,Generator<T> gen, int size) {
		T[] a = (T[])Array.newInstance(type, size);
		return CollectionData.list(gen, a.length).toArray(a);
	}
}
