package com.hf.lesson11;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 插入中间位置
 * @author ciker
 * @desc   
 *
 */
public class Test14 {
	public static void main(String[] args) {
		LinkedList<Integer> list=new LinkedList<>();
		ListIterator<Integer> listIterator = list.listIterator();
		for(int i=0;i<5;i++) {
			int size = list.size();
			listIterator = list.listIterator(size/2);
			listIterator.add(i);
		}
	}
}
