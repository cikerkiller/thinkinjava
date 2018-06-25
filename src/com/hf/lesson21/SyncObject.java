package com.hf.lesson21;

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}
class DualSynch {
	private Object syncObject = new Object();
	public synchronized void  f() {
		for(int i=0;i<5;i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	public void g() {
		synchronized (syncObject) {// 在另一个对象上加锁，跟上一个同步是独立  
			for(int i=0;i<5;i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}
