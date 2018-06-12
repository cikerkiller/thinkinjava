package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.hf.lesson16.Generated;
import com.hf.lesson16.RandomGenertor;

public class MyComparableTest {
	public static void main(String[] args) {
		List<MyComparable> list=new ArrayList<>(CollectionData.list(new RandomGenertor.SimpleComparable(), 5));
		System.out.println(list);
		Collections.sort(list,new MyComparable.MyComparator());// 字母序排序
		System.out.println(list);
		
		MyComparable[] a = Generated.array(MyComparable.class,new RandomGenertor.SimpleComparable(),5);
		System.out.println(Arrays.toString(a));
		Arrays.sort(a,new MyComparable.MyComparator());//排序的话一定要MyComparable实现comparable
		System.out.println(Arrays.toString(a));
		
		
	}
}
