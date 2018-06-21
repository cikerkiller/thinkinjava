package com.hf.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{
	private IntGenerator gen;
	private final int id;
	public EvenChecker(IntGenerator gen, int ident) {
		this.gen = gen;
		this.id = ident;
	}
	
	@Override
	public void run() {
		while(!gen.isCanceled()) {
			System.out.println("gen.isCanceled()"+Thread.currentThread().getName());
			int val = gen.next();// 因为此处返回的偶数所有线程会陷入无限循环
			if(val % 2 != 0) {
				System.out.println(val + " not even!");
				gen.cancel();
			}
		}
	}
	
	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C to exit"); 
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i=0;i<count;i++) {
			pool.execute(new EvenChecker(gp, i));
		}
		pool.shutdown();
	}
	
	public static void test(IntGenerator gp) {
		test(gp, 10);
	}

}
