package com.hf.lesson21;

import java.util.concurrent.TimeUnit;

public class LiftOff implements Runnable{
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public LiftOff() {}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#"+id+"("+(countDown > 0 ? countDown : "LiftOff!") + "),";
	}
	@Override
	public void run() {
		while(countDown-- > 0) {
			System.out.println(status());
			// 对线程调度器（Java线程机制的一部分，可以将CPU从一个线程转移给另一个线程）的一种建议，是选择性的，
			// 建议：已经执行完生命周期中最重要的部分了，此刻是切换给其他任务执行一段时间的最好时机
//			Thread.yield();
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		// 单一线程(主线程)创建多个线程
		for(int i=0;i<5;i++) {
			Thread t = new Thread(new LiftOff());
			t.start();
		}
		System.out.println("waiting for LiftOff");
	}
}
