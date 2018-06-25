package com.hf.lesson21.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 计数信息量
public class SemaphoreDemo {
	final static int SIZE = 25;
	public static void main(String[] args) throws Exception{
		final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < SIZE; i++) {
			exec.execute(new CheckoutTask<Fat>(pool));
		}
		System.out.println("all checkoutTask created");
		
		List<Fat> list = new ArrayList<>();
		for(int i=0;i<SIZE;i++) {
			Fat item = pool.checkOut();
			item.operation();
			list.add(item);
		}
		
		Future<?> blocked = exec.submit(new Runnable() {
			@Override
			public void run() {
				try {
					pool.checkOut();
				} catch (InterruptedException e) {
					System.out.println("checkout interrupted");
				}
			}
		});
		
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);// 如果运行中传入true则中断
		for(Fat f : list) {
			pool.checkIn(f);
		}
		for(Fat f : list) {
			pool.checkIn(f);
		}
		exec.shutdown();
	}
}
class CheckoutTask<T> implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private Pool<T> pool;
	public CheckoutTask(Pool<T> pool) {
		this.pool = pool;
	}
	public void run() {
		try {
			T item = pool.checkOut();
			System.out.println(this + "checked out "+item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + "checked in "+item);
			pool.checkIn(item);
		} catch (InterruptedException e) {
		}
	}
	public String toString() {
		return "CheckoutTask "+id+" ";
	}
	
}
