package com.hf.lesson11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimpleCollection {
	public static void main(String[] args) {
		Set<Integer> c=new HashSet<>();
		for(int i=0;i<5;i++)
			c.add(i);
		Iterator<Integer> iterator = c.iterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());
	}
}
