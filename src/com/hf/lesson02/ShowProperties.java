package com.hf.lesson02;

/**
 * 
 * @author ciker
 * @desc   
 *
 */
public class ShowProperties {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.library.path"));
	}
}
