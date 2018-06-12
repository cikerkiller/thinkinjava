package com.hf.lesson05;

public enum Coin {
	ONE,TWO,THREE;
	public static void main(String[] args) {
		Coin coin=Coin.TWO;
		System.out.println(coin);
		for(Coin c:Coin.values()) {
			System.out.println("c="+c+",ora="+c.ordinal());
		}
		sw(coin);
	}
	public static void sw(Coin c) {
		switch(c) {  
		case ONE:
			System.out.println("one");break;
		case TWO:
			System.out.println("two");
		case THREE:
			System.out.println("THREE");break;
		}
	}
}
