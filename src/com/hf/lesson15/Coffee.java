package com.hf.lesson15;

public class Coffee {
	private static long counter = 0;
	private final long id = counter++;
	public String toString() {
		return getClass().getSimpleName()+" "+id;
	}
}

class Mocha extends Coffee{}
class Latte extends Coffee{}
class Cappuccino extends Coffee{}
class Americano extends Coffee{}
class Breve extends Coffee{}
