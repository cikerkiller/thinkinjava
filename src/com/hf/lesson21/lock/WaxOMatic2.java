package com.hf.lesson21.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 显示加锁lock,condition唤醒与等待
public class WaxOMatic2 {
	public static void main(String[] args) throws Exception{
		Car car  = new Car();
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(new WaxOff(car));
		pool.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		pool.shutdownNow();
	}
}
class Car {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;
	public void waxed() {
		lock.lock();
		try {
			waxOn = true;
			condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
	public void buffed() {
		lock.lock();
		try {
			waxOn = false;
			condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public void waitForWaxing() throws InterruptedException{
		lock.lock();
		try {
			while(waxOn == false) {
				condition.await();
			}
		}finally {
			lock.unlock();
		}
		
	}
	public synchronized void waitForBuffing() throws InterruptedException{
		lock.lock();
		try {
			while(waxOn ==true) {
				condition.await();
			}
		}finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println("Wax On ");
				TimeUnit.MILLISECONDS.sleep(220);
				
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("wo Exiting via interrupt");
		}
		System.out.println("wo Ending Wax on task");
	}
}

class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car c) {
		car = c;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.println("Wax off");
				TimeUnit.MILLISECONDS.sleep(220);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("wf Exiting via interrupt");
		}
		System.out.println("Ending Wax off task");
	}
	
}


