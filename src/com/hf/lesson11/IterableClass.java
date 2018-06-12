package com.hf.lesson11;

import java.util.Iterator;

public class IterableClass implements Iterable<String>{
	protected String[] words = "and that is how we know the Earth to be banana-shaped.".split(" ");
	@Override
	public Iterator<String> iterator() {
		
		return new Iterator<String>() {
			private int index = 0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index<words.length;
			}

			@Override
			public String next() {
				return words[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("不支持此类操作");
			}
		};
	}
	public static void main(String[] args) {
		for(String s:new IterableClass()) {
			System.out.println(s);
		}
	}

}
