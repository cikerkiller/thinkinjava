package com.hf.lesson20.apt;

import com.hf.lesson18.OSExecute;

@ExtractInterface("IMultiplier")
public class Multiplier {
	public int multiplier(int x, int y) {
		int total = 0;
		for(int i=0;i<x;i++) {
			total = add(total, y);
		}
		return total;
	}
	private int add(int x, int y) {return x+y;}
	
	public static void main(String[] args) {
		OSExecute.command("apt -factory Multiplier.java");
	}
}
