package com.hf.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 练习一
public class TestRunnable implements Runnable{
	private static int count = 0;
	private final int id = count++;
	public TestRunnable() {
		System.out.println("线程"+id+"启动");
	}
	@Override
	public void run() {
		for(int i=0;i<3;i++) {
			System.out.println("test..."+id);
			Thread.yield();
		}
		System.out.println("线程"+id+"关闭");
	}
	
	public static void main(String[] args) {
//		for(int i=0;i<10;i++) {
//			new Thread(new TestRunnable()).start();
//		}
		
//		ExecutorService pool = Executors.newCachedThreadPool();// 在程序执行过程中通常会创建与所需数量相同的线程，然后在它回收旧线程时停止创建新线程
//		ExecutorService pool = Executors.newFixedThreadPool(5);// 创建固定的线程,重复利用
		ExecutorService pool = Executors.newSingleThreadExecutor();// 创建单一的线程,下一个任务等待当前任务结束
		for(int i=0;i<10;i++) {
			pool.execute(new TestRunnable());
		}
		pool.shutdown();
		
	}
}
