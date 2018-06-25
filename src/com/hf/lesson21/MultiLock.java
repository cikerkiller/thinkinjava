package com.hf.lesson21;

// 被互斥阻塞
public class MultiLock {
	public synchronized void f1(int count) {
		if(count-- > 0) {
			System.out.println("f1 calling f2 with count "+count+"   "+Thread.currentThread().getName());
			f2(count);
		}
	}
	
	public synchronized void f2(int count) {
		if(count-- > 0) {
			System.out.println("f2 calling f1 with count "+count+"   "+Thread.currentThread().getName());
			f1(count);
		}
	}
	
	public static void main(String[] args) {
		final MultiLock l = new MultiLock();
		for(int i=0;i<5;i++) {
			new Thread() {
				public void run() {
					l.f1(10);
				}
			}.start();
		}
	}
}
