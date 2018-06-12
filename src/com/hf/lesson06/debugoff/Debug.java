package com.hf.lesson06.debugoff;

public class Debug {
	public static void debug() {
		System.out.println("com.hf.lesson06.debugoff.Debug");
	}
	public static void main(String[] args) {
		Test.f();
	}
	protected void aa() {
		
	}
}
class Test1{
	public static void main(String[] args) {
		Debug debug=new Debug();
		debug.aa();
	}
}