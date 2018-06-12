package com.hf.lesson15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericVarargs {
	@SafeVarargs
	public static <T> List<T> makeList(T...args){
		List<T> result = new ArrayList<>();
		for(T item:args) {
			result.add(item);
		}
		return result;
	}
	public static void main(String[] args) {
		List<? extends Object> ls = makeList("1");
		System.out.println(ls);
		ls = makeList("1","2",2);
		System.out.println(ls);
		Arrays.asList("");
	}
}
