package com.hf.lesson21;

import java.util.Random;

import com.hf.lesson15.Fibonacci;

// 练习二
public class TestRunnable2 implements Runnable{
	private int n;
	public TestRunnable2(int n) {
		this.n = n;
	}
	@Override
	public void run() {
		Fibonacci f = new Fibonacci(n);
		System.out.println(n+": "+f.all());
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			new Thread(new TestRunnable2(new Random().nextInt(20))).start();
		}
	}

}
