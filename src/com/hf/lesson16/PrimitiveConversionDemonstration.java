package com.hf.lesson16;

import java.util.Arrays;

// 类名：基本类型数组转换示例
public class PrimitiveConversionDemonstration {
	public static void main(String[] args) {
		Integer[] integers = Generated.array(Integer.class, new RandomGenertor.Integer(), 5);
//		int[] ints = Generated.array(Integer.class, new RandomGenertor.Integer(), 5);// 自动包装机制不能用于数组
		int[] a = ConvertTo.primitive(integers);
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		int indexValue=Arrays.binarySearch(a, 555);// -1表示未找到 大于等于0的为key在数组中的下标,必须是已经排好序的数组
		System.out.println(indexValue);
		System.out.println(Arrays.toString(ConvertTo.primitive(Generated.array(Boolean.class, new RandomGenertor.Boolean(), 5))));
	}
}
