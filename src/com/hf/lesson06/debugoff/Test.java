package com.hf.lesson06.debugoff;

import com.hf.lesson07.SprinklerSystem;

public class Test {
	protected static void f() {
		System.out.println("Test.f()");
	} 
	public static void main(String[] args) {
		SprinklerSystem sprinklerSystem=new SprinklerSystem();
		System.out.println(sprinklerSystem);
	}
}
