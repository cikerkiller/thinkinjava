package com.hf.lesson17;

import java.util.Comparator;
//练习40
public class MyComparable implements Comparable<MyComparable> {
	private String first;
	private String second;
	
	
	public MyComparable() {
		super();
	}

	public MyComparable(String first, String second) {
		super();
		this.first = first;
		this.second = second;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	@Override
	public int compareTo(MyComparable o) {
		return first.compareTo(o.first);
	}

	@Override
	public String toString() {
		return "MyComparable [first=" + first + "]";
	}
	
	public static class MyComparator implements Comparator<MyComparable>{

		@Override
		public int compare(MyComparable o1, MyComparable o2) {
			return String.CASE_INSENSITIVE_ORDER.compare(o1.first, o2.first);
		}
		
	}
}
