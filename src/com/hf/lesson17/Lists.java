package com.hf.lesson17;
// list的功能方法

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Lists {
	private static boolean b;
	private static String s;
	private static int i;
	private static Iterator<String> it;
	private static ListIterator<String> lit;
	
	public static void basicTest(List<String> a) {
		a.add(1,"x");
		System.out.println(a);
		a.add("x");
		System.out.println(a);
		a.addAll(Countries.names(25));
		System.out.println(a);
		a.addAll(3, Countries.names(25));
		System.out.println(a);
		b=a.contains("1");
		System.out.println(b);
		b=a.containsAll(Countries.names(25));
		System.out.println(b);
		s=a.get(1);
		System.out.println(s);
		i=a.indexOf("1");
		System.out.println(i);
		b=a.isEmpty();
		System.out.println(b);
		it=a.iterator();
		System.out.println(it);
		lit=a.listIterator();
		System.out.println(lit);
		lit=a.listIterator(3);
		System.out.println(lit);
		i=a.lastIndexOf("1");
		System.out.println(i);
		a.remove(1);
		System.out.println(a);
		a.remove("3");
		System.out.println(a);
		a.set(1, "Y");
		System.out.println(a);
		a.retainAll(Countries.names(25));
		System.out.println(a);
		i=a.size();
		System.out.println(i);
		a.clear();
		System.out.println(a);
	}
	
	public static void iterMotion(List<String> a) {
		ListIterator<String> it =a.listIterator();
		b=it.hasNext();
		b=it.hasPrevious();
		s=it.next();
		i=it.nextIndex();
		s=it.previous();
		i=it.previousIndex();
	}
	
	
	public static void main(String[] args) {
//		iterMotion(new ArrayList<String>(Countries.names(1)));
		basicTest(new ArrayList<String>(Countries.names(1)));
	}
	
}
