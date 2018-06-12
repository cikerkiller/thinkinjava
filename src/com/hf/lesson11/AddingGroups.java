package com.hf.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AddingGroups {
	public static void main(String[] args) {
		Collection<Integer> collection=new ArrayList<>(Arrays.asList(1,2,3,4,5));
		
		Integer[] moreInts= {6,7,8,9,10};
		
		collection.addAll(Arrays.asList(moreInts));
		
		Collections.addAll(collection, 11,12,13,14,15);
		
		Iterator<Integer> iterator = collection.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("=======================");
		Collections.addAll(collection, moreInts);
		iterator = collection.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		List<Integer> list=Arrays.asList(17,15,18,12);
		System.out.println("==="+list.set(1, 99));// set方法返回的是替换的那个元素
//		list.add(12); // 运行时异常  底层表示的是数组 返回的Arraylist是Arrays的静态内部类
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
}	
