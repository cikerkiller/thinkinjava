package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class ListSortSearch {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>(Utilities.list);
		list.addAll(Utilities.list);
		System.out.println(list);
		Collections.shuffle(list);// 打乱顺序
		System.out.println(list);
		Collections.shuffle(list,new Random(47));// 打乱顺序
		System.out.println(list);
		
		ListIterator<String> it = list.listIterator(10);// 从下标为10开始之后所有的数据
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
		System.out.println(list);
		
		Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
		System.out.println(list);
		
		String key = list.get(7);
		int search = Collections.binarySearch(list, key,String.CASE_INSENSITIVE_ORDER);
		System.out.println(search);
		
	}
}	
