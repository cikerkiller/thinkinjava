package com.hf.lesson12;

public class MyException extends RuntimeException{
	private String message;
	public MyException(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public static void main(String[] args) {
		MyException exception=new MyException("this is a test1");
		exception.f();
	}
	
	public void f() {
		throw new MyException("this is a test");
	}
}
