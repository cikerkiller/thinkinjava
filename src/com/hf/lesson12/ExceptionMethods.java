package com.hf.lesson12;

import java.util.Arrays;

public class ExceptionMethods {
	public static void main(String[] args) {
		try {
			throw new Exception("My exception");
		} catch (Exception e) {
			System.out.println("e.getMessage()："+e.getMessage());
			System.out.println("e.getLocalizedMessage()："+e.getLocalizedMessage());
			System.out.println("e："+e);
			System.out.println("e.printStackTrace"+Arrays.asList(e.getStackTrace()));
			e.printStackTrace(System.out);
		}
	}
}
