package com.hf.lesson01;

public abstract class AbstractTest extends ObjectTest{
	public String test="AbstractTest";
	public void test1() {
		System.out.println("this is a test1");
	}
	
}
class ObjectTest{
	public String test="ObjectTest";
}