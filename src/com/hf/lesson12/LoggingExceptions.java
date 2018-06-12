package com.hf.lesson12;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LoggingExceptions {
	public static void main(String[] args) {
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught "+e);
		}
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught "+e);
		}
		
		
	}
}

class LoggingException extends Exception{
	private static Logger logger = Logger.getLogger("LoggingException");
	
	public LoggingException() {
		StringWriter trace=new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
	
	static void LogException(Exception e) throws NullPointerException {
		StringWriter trace=new StringWriter();
		e.printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
//		throw new NullPointerException();
	}
	public static void main(String[] args) {
		try {
			throw new NullPointerException();
		} catch (NullPointerException e) {
//			LogException(e);
			System.out.println(e.getLocalizedMessage());
		}
	}
}
