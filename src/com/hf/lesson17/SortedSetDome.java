package com.hf.lesson17;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
// 按对象的比较函数对元素进行排序  
public class SortedSetDome {
	public static void main(String[] args) {
		SortedSet<String> sortedSet = new TreeSet<>();
		Collections.addAll(sortedSet, "this is a test sd a d ads sda dsw aa".split(" "));
		System.out.println(sortedSet);
		String low = sortedSet.first();
		String last = sortedSet.last();
		System.out.println("low="+low);
		System.out.println("last="+last);
	
		Iterator<String> iterator = sortedSet.iterator();
		for(int i=0;i<=6;i++) {
			if(i==3) 
				low=iterator.next();
			if(i==6) 
				last=iterator.next();
			else 
				iterator.next();
		}
		System.out.println("low="+low);
		System.out.println("last="+last);
		System.out.println(sortedSet.subSet(low, last));// 不包含last
		System.out.println(sortedSet.headSet(last));// 从0到last，不包含last
		System.out.println(sortedSet.tailSet(low));// 从low开始到结束，包含low
	
	}
}
