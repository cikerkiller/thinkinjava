package com.hf.lesson18;

public class OSExecuteException extends RuntimeException {
	public OSExecuteException(String why) {
		super(why);
	}
	public OSExecuteException(Throwable why) {
		super(why);
	}
}
