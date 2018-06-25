package com.hf.lesson21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 线程本地存储
public class ThreadLocalVariableHolder {
	// 可以为使用相同变量的每个不同的线程都创建不同的存储
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);
		@Override
		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};
	
	public static void increment() {
		value.set(value.get()+1);
	}
	public static int get() {
		return value.get();
	}
	
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.MILLISECONDS.sleep(3);
		exec.shutdown();
	}
	
}
class Accessor implements Runnable {
	private final int id;
	public Accessor(int idn) {
		this.id = idn;
	}
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
//			Thread.yield();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String toString() {
		return "#"+id+": "+ThreadLocalVariableHolder.get();
	}
	
}
