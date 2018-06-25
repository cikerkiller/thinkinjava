package com.hf.lesson21;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): "+captured);
		}finally {
			if(captured) {
				lock.unlock();
			}
		}
	}
	
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): "+captured);
		}finally {
			if(captured) {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) throws Exception{
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		
		new Thread() {
			{
				setDaemon(true);
			}
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		TimeUnit.SECONDS.sleep(1);// 此处主线程休息一秒,后台线程运行
//		Thread.yield();// 没有用，这个看平台或者JVM，此处主线程没有把运行机会给后台线程
		al.untimed();
		al.timed();
	}
	
}
