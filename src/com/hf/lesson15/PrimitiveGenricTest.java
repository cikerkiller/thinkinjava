package com.hf.lesson15;

import java.util.Random;

public class PrimitiveGenricTest {
	public static void main(String[] args) {
		String[] strings = FArray.fill(new String[5], new RandomGenerator.String());
		for (String s : strings) {
			System.out.print(s + " ");
		}

		Integer[] in = FArray.fill(new Integer[5], new RandomGenerator.Integer());
		for (int i : in) {
			System.out.print(i + " ");
		}
	}
}

class FArray {
	public static <T> T[] fill(T[] a, Generator<T> gen) {
		for (int i = 0; i < a.length; i++) {
			a[i] = gen.next();
		}
		return a;
	}
}

class RandomGenerator {
	private static Random rand = new Random(47);

	static class String implements Generator<java.lang.String> {
		java.lang.String str = "abcdefghijklmnopqrstuvwxyz";

		@Override
		public java.lang.String next() {
			char[] array = str.toCharArray();
			return java.lang.String.valueOf(array[rand.nextInt(array.length)]);
		}

	}

	static class Integer implements Generator<java.lang.Integer> {
		java.lang.String str = "abcdefghijklmnopqrstuvwxyz";

		@Override
		public java.lang.Integer next() {
			char[] array = str.toCharArray();
			return java.lang.Integer.valueOf(array[rand.nextInt(array.length)]);
		}

	}

}
