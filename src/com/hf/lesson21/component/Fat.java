package com.hf.lesson21.component;

public class Fat {
	@SuppressWarnings("unused")
	private volatile double d;
	private static int counter = 0;
	private final int id = counter++;
	public Fat() {
		for(int i=0;i<1000;i++) {
			d += (Math.PI + Math.E) / (double)i;// 模拟创建代价高昂
		}
	}
	public void operation() {
		System.out.println(this);
	}
	public String toString() {
		return "Fat id: "+id;
	}
}
