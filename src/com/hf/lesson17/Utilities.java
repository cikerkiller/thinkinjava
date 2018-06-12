package com.hf.lesson17;
// collections的一些方法

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utilities {
	static List<String> list = Arrays.asList("one Two three Four five six one".split(" "));
	public static void main(String[] args) {
		System.out.println(list);
		System.out.println("'list' disjoint(Four)?:"+Collections.disjoint(list, Collections.singletonList("Four")));
		
		System.out.println("max : "+Collections.max(list,String.CASE_INSENSITIVE_ORDER));
		System.out.println("min : "+Collections.min(list));
		System.out.println(Collections.nCopies(3, "ds"));
		System.out.println(Collections.frequency(list, "one"));// 返回集合中one的个数
		Collections.sort(list);
		System.out.println(Collections.binarySearch(list, "three"));
		System.out.println(list);
		Collections.swap(list, 0, list.size()-1);
		System.out.println(list);
	}
}
