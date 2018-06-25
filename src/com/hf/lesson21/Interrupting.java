package com.hf.lesson21;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 中断 可以中断sleep以及任何需要interruptexception的方法，不可中断获得对象锁的线程和在读写的io操作
public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException {
		Future<?> future = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		future.cancel(true);
		System.out.println("Interrupt sent to "+r.getClass().getName());
	}
	
	public static void main(String[] args) throws Exception{
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.exit(0);
	}
}

class SleepBlocked implements Runnable {
	
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		}catch(InterruptedException e) {
			// 走到这里说明已经中断了
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

class IOBlocked implements Runnable {
	private InputStream in;
	public IOBlocked(InputStream in) {
		this.in = in;
	}
	@Override
	public void run() {
		try {
			System.out.println("waiting for read():");
			in.read();
		}catch(IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from blocked i/o");
			}else{
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}
	
}

class  SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while(true) {
			Thread.yield();
		}
	}
	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f();
			}
		}.start();
	}
	@Override
	public void run() {
		System.out.println("trying to call f()");
		f();
		System.out.println("exiting synchronizedBlockked.run()");
	}
	
}

