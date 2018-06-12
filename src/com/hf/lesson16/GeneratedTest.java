package com.hf.lesson16;

import java.util.Arrays;

public class GeneratedTest {
	public static void main(String[] args) {
		Integer[] a = {1,2,3,5,4,6,5};
		System.out.println(Arrays.toString(a));
		Generated.array(a, new RandomGenertor.Integer());
		System.out.println(Arrays.toString(a));
		
		Integer[] integers = Generated.array(Integer.class, new RandomGenertor.Integer(), 15);
		System.out.println(Arrays.toString(integers));
	}
}
