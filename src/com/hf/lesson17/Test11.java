package com.hf.lesson17;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

// 练习11
public class Test11 implements Comparable<Test11> {
	Random rand = new Random();
	private Integer i = rand.nextInt(100);
	@Override
	public int compareTo(Test11 o) {
		if(i > o.i) {
			return 1;
		}else if(i == o.i) {
			return 0;
		}
		return -1;
	}
	public String toString() {
		return "Test "+i;
	}
	
	public static void main(String[] args) {
		PriorityQueue<Test11> p = new PriorityQueue<>();
		for(int i= 0; i < 10; i++) {
			p.add(new Test11());
		}
		while(!p.isEmpty()) {
			System.out.println(p.remove());
		}
		
		Deque<String> d = new LinkedList<String>();
		d.add("this");
		for(String s:d) {
			System.out.println(s);
		}
	}
}
