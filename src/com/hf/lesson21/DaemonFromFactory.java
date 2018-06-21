package com.hf.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable{

	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread()+" "+this);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws Exception{
		ExecutorService pool = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i=0;i<10;i++) {
			pool.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
	}

}
