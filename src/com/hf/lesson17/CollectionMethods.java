package com.hf.lesson17;

import java.util.Arrays;
import java.util.Collection;

//Collection的子类的最基本的共同特性
public class CollectionMethods {
	public static void main(String[] args) {
//		Collection<String> c = new ArrayList<>();
		Collection<String> c = new SlowSet<>();
		c.addAll(Countries.names(6));
		System.out.println(c);
		c.add("ten");
		System.out.println(c);
		Object[] array = c.toArray();
		String[] a=new String[1];
		a[0]="sdas";
		String[] array2 = c.toArray(a);
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(array2));
		
	}
}
