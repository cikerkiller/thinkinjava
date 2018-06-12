package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FailFast {
	public static void main(String[] args) {
		Collection<String> c = new ArrayList<>();
		Iterator<String> it = c.iterator();   
		c.add("");
//		it.next();//ConcurrentModificationException 防止多个进程同时修改同一个容器
	}
}
