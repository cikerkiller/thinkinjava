package com.hf.lesson17;

import java.util.AbstractList;

/**
 * 抽象list
 * @author ciker
 * @desc   只读list
 *
 */
public class CountingIntegerList extends AbstractList<Integer>{
	private int size;
	
	public CountingIntegerList(int size) {
		this.size = size < 0 ? 0 : size;
	}
	
	@Override
	public Integer get(int index) {
		return Integer.valueOf(index);// index为下标，此处产生数据
	}

	@Override
	public int size() {
		return size;
	}
	public static void main(String[] args) {
		System.out.println(new CountingIntegerList(20));
		System.out.println(new CountingIntegerList(5));
	}
}
