package com.hf.lesson21.component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// CountDownLatch 锁存器 被用来同步一个或多个任务，强制他们等待由其他任务执行的一组操作的完成
public class CountDownLatchDemo {
	static final int SIZE = 100;
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);// 初始值
		for(int i=0;i<10;i++) {
			exec.execute(new WaitingTask(latch));// 
		}
//		WaitingTask 必须等待TaskPortion
		for(int i=0;i<150;i++) {
			exec.execute(new TaskPortion(latch));
		}
		
		System.out.println("Launched all tasks");
		exec.shutdown();
	}
}

class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;
	TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();// 完成一个就从计数器减一直到为0
		} catch (InterruptedException e) {
			System.out.println("TaskPortion InterruptedException "+id);
		}
	}
	
	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this+" completed "+latch.getCount());
	}
	
	public String toString() {
		return String.format("%1$-3d ", id);
	}
}

class WaitingTask implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;
	WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}
	public String toString() {
		return String.format("WaitingTask %1$-3d ", id);
	}
	@Override
	public void run() {
		try {
			latch.await();// 等待其他任务被解决,直到计数器值为0
//			latch.await(50, TimeUnit.MILLISECONDS);// 等50毫秒后执行
			System.out.println("Latch barrier passed for "+this);
		} catch (InterruptedException e) {
			System.out.println(this+" interrupted");
		}
		
	}
}
