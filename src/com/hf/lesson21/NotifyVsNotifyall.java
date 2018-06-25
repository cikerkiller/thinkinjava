package com.hf.lesson21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 
public class NotifyVsNotifyall {
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;
			@Override
			public void run() {
				if(prod) {
					System.out.println("\nnotify");
					Task.blocker.prod();
					prod = false;
				}else {  
					System.out.println("\nnotifyall");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);// 时间定时器，每4/10秒执行run方法一次，交替 的在task.blocker上调用notify和notifyall来唤醒一个线程或全部线程，对第二个task没有影响
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();
		System.out.println("\ntimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		Task2.blocker.prodAll();// 此刻才会唤醒task2
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nshutdown");
		exec.shutdownNow();// interrupt all tasks
	}
}
class Blocker {
	synchronized void waitingCall(String name) {
		try {
//			while(!Thread.interrupted()) {
			for(;;) {
				wait();// 等待中断也会抛异常
				System.out.print(Thread.currentThread()+"=====name-->"+name+"   ");
			}
		}catch(InterruptedException e) {
			System.out.println(name+"   blocker..."+e);
		}
	}
	synchronized void prod() {
		notify();
	}
	synchronized void prodAll() {
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker =new Blocker();
	@Override
	public void run() {
		blocker.waitingCall("task1");
	}
}
class Task2 implements Runnable {
	static Blocker blocker =new Blocker();
	@Override
	public void run() {
		blocker.waitingCall("task2");
	}
}
