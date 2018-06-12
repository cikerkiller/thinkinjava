package com.hf.lesson17.test;
// 测试基类
public abstract class Test<C> {
	String name;
	public Test(String name) {
		this.name=name;
	}
	abstract int test(C container,TestParam tp);
}
