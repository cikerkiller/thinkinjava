package com.hf.lesson21;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable{
	
	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread()+" "+this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		for(int i=0;i<10;i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);// 必须在线程启动前设置为后台线程
			daemon.start();
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);// 当非后台线程结束时，会同时杀死进程中的所有后台线程
	}

}
