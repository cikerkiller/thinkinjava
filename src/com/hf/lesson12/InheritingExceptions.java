package com.hf.lesson12;

public class InheritingExceptions {
	public void f() throws SimpleException{
		System.out.println("Throws simpleException from f()");
		throw new SimpleException();
	}
	public static void main(String[] args) {
		InheritingExceptions exceptions=new InheritingExceptions();
		try {
			exceptions.f();
		} catch (SimpleException e) {
			System.out.println("Caught it!");
		}
	}
}
class SimpleException extends Exception{
	
}


