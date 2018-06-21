package com.hf.lesson20.test;

public class AtUnitExample {
	public String methodOne() {
		return "this";
	}
	@Test public boolean methodOneTest() {
		return methodOne().equals("this");
	}
}
