package com.hf.lesson21.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.hf.lesson21.LiftOff;

public class TestBlockingQueues {
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	static void getKey(String message) {
		System.out.println(message);
		getKey();
	}
	
	static void test(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
//		Thread t = new Thread(runner);
//		t.start();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(runner);
//		for(int i=0;i<5;i++) {
//			runner.add(new LiftOff(5));
//		}
		getKey("Press 'Enter' ("+msg+")");
		exec.shutdownNow();
		System.out.println("Finished "+msg+" test");
	}
	
	static class AddLiftoff extends Thread {
		public AddLiftoff() {
			start();
		}
		@Override
		public void run() {
			test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
			test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
			test("SynchronousQueue", new SynchronousQueue<LiftOff>());
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new AddLiftoff();
		
	}
}
class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets ;
	public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
		this.rockets = rockets;
	}
	
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("interrupted during put()");
		}
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("waking from take() ");
		}
		System.out.println("Exiting liftoffrunner");
	}
	
}
