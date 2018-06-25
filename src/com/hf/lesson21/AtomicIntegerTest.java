package com.hf.lesson21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable{
	private AtomicInteger i = new AtomicInteger(0);
	public int getValue() { return i.get();}
	private void evenIncrement() {i.addAndGet(2);}
	@Override
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Aborting");
				System.exit(0);
			}
		}, 5000);// 定时5秒
		
		ExecutorService  exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ai = new AtomicIntegerTest();
		exec.execute(ai);
		while(true) {
			int val = ai.getValue();
			if(val % 2 !=0) {// 当是奇数时退出
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
