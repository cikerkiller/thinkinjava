package com.hf.lesson15;

import java.util.Arrays;
import java.util.List;

public class CompilerIntelligence {
	public static void main(String[] args) {
		List<? extends Fruit> flist = Arrays.asList(new Apple());
		Apple apple = (Apple) flist.get(0);
		boolean contains = flist.contains(apple);
		System.out.println(contains);// false
		System.out.println(flist.contains(new Object()));// false
		int indexOf = flist.indexOf(apple);
		System.out.println(indexOf);// -1

	}
}
