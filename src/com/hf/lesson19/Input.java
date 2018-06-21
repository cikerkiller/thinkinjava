package com.hf.lesson19;

import java.util.Random;

// 状态机
public enum Input {
	NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100), TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
	
	ABORT_TRANSACTION {
		public int amount() {
			throw new RuntimeException("abort...");
		}
	},
	STOP {
		public int amount() {
			throw new RuntimeException("stop...");
		}
	};
	int value;
	Input(int value){
		this.value = value;
	}
	Input(){}
	int amount() {return value;}
	
	static Random  rand = new Random();
	public static Input randomSelection() {
		return values()[rand.nextInt(values().length)];
	}
	
	
}
