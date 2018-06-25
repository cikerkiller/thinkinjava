package com.hf.lesson21;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// lock
public class Interrupting2 {
	public static void main(String[] args) throws Exception{
		Thread t = new Thread(new Blocked2());
		t.start();
		
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Issuing t.interrupt");
		t.interrupt();
	}
}
class BlockedMutex {
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		lock.lock();
	}
	public void f() {
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException f");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();
	@Override
	public void run() {
		System.out.println("Waiting for f in BlockedMutex");
		blocked.f();
		System.out.println("broken out of blocked call");
	}
	
}