package com.hf.lesson21;

import java.util.concurrent.TimeUnit;

// 检查阻塞
public class InterruptingIdiom {
	public static void main(String[] args) throws Exception
	{
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(2100);
		t.interrupt();
	}
}
class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int ident) {
		this.id = ident;
		System.out.println("NeedsCleanup "+id);
	}
	public void cleanup() {
		System.out.println("cleaning up "+id);
	}
}
class Blocked3 implements Runnable {
	private volatile double d = 0.0;
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);// 如果中断在此处及之前被调用会直接异常退出  阻塞
					// 如果在之后调用会循环结束  非阻塞
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("calcullating");
						for(int i=1;i<2500000;i++) {
							d = d + (Math.PI + Math.E) / d;
						}  
						System.out.println("Finished ..");
					} finally {
						n2.cleanup();
					}
					
				}finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via..");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}
	
}
