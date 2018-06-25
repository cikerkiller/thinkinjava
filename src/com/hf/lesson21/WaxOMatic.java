package com.hf.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {
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
	private boolean waxOn = false;
	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}
	
	public synchronized void waitForWaxing() throws InterruptedException{
		while(waxOn == false) {
			wait();
		}
	}
	public synchronized void waitForBuffing() throws InterruptedException{
		while(waxOn == true) {
			wait();
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


