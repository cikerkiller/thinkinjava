package com.hf.lesson18;

import java.util.Arrays;
import java.util.Collection;

public class PPrint {
	public static String pformat(Collection<?> c) {
		if(c.size() == 0) {
			return "[]";
		}
		StringBuilder sbu = new StringBuilder("[");
		for(Object o : c) {
			if(c.size()!=1) {
				sbu.append("\n  ");
			}
			sbu.append(o);
		}
		if(c.size()!=1) {
			sbu.append("\n");
		}
		sbu.append("]");
		return sbu.toString();
	}
	
	public static void pprint(Collection<?> c) {
		System.out.println(pformat(c));
	}
	
	public static void pprint(Object[] c) {
		System.out.println(pformat(Arrays.asList(c)));
	}
}
