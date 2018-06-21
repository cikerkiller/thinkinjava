package com.hf.lesson21;

import java.util.concurrent.TimeUnit;

public class Daemons {
	public static void main(String[] args) throws Exception{
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);// 后台线程创建的线程都是后台线程
		d.start();
		System.out.println("d.isDaemon() = "+d.isDaemon());
		TimeUnit.SECONDS.sleep(1);
	}
}
class Daemon implements Runnable {
	private Thread[] t = new Thread[10];
	@Override
	public void run() {
		for(int i=0;i<t.length;i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn "+i+ " started, ");
		}
		
		for(int i=0;i<t.length;i++) {
			System.out.print("t["+i+"].isDaemon() = "+t[i].isDaemon()+",");
		}
//		while(true) {
//			Thread.yield();
//		}
	}
	
}
class DaemonSpawn implements Runnable {
	
	@Override
	public void run() {
		while(true) {
			Thread.yield();
		}
	}
	
}