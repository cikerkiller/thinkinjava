package com.hf.lesson21;

import java.util.concurrent.TimeUnit;

public class DaemonsDontRunFinally {
	public static void main(String[] args) throws Exception{
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					System.out.println("Starting daemon1");
//					TimeUnit.SECONDS.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}finally {
//					System.out.println("finally1");
//				}
//			}
//		}).start();
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("Starting daemon2");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					System.out.println("finally2");
				}
			}
		});
		t.setDaemon(true);
		t.start();
		TimeUnit.MILLISECONDS.sleep(1);
	}
}
