package com.hf.lesson21;

public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	public static synchronized int nextSerialNumber() {
		return serialNumber++;
	}
}
