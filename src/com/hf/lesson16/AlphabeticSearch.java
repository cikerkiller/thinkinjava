package com.hf.lesson16;

import java.util.Arrays;
import java.util.Collections;

public class AlphabeticSearch {
	public static void main(String[] args) {
		String[] sa = Generated.array(new String[30], new RandomGenertor.String(5));
		
		Arrays.sort(sa,String.CASE_INSENSITIVE_ORDER);
		System.out.println(Arrays.toString(sa));
		
		int search = Arrays.binarySearch(sa, sa[10], String.CASE_INSENSITIVE_ORDER);
		System.out.println(search);
		
		int[] a =ConvertTo.primitive(Generated.array(Integer.class, new RandomGenertor.Integer(), 6));
		Integer[] aa=Generated.array(Integer.class, new RandomGenertor.Integer(), 6);
//		Arrays.sort(a,Collections.reverseOrder());// 基本类型数组不能使用comparator排序
		Arrays.sort(aa,Collections.reverseOrder());
		System.out.println(Arrays.toString(aa));
		
	}
}
