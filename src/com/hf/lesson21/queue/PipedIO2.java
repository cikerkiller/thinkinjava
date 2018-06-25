package com.hf.lesson21.queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// 练习30
public class PipedIO2 {
	public static void main(String[] args) throws Exception {
		Sender1 sender = new Sender1();
		Receiver1 receiver = new Receiver1(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}
class  Sender1 implements Runnable {
	private Random rand = new Random(47);
	private LinkedBlockingQueue<Character> queue = new LinkedBlockingQueue<>();
	public LinkedBlockingQueue<Character> getQueue() {
		return queue;
	}
	public void run() {
		try {
			while(true) {
				for(char c = 'A'; c <= 'z'; c++) {
					queue.put(c);
				}
			}
		}catch(InterruptedException e) {
			System.out.println(e+" Sender sleep exception");
		}
	}
}

class Receiver1 implements Runnable {
	private LinkedBlockingQueue<Character> queue;
	public Receiver1(Sender1 sender) throws Exception {
		queue = sender.getQueue();
	}
	public void run() {
		try {
			while(true) {
				System.out.println("Read: "+queue.take()+", ");
			}
		}catch (InterruptedException e) {
			System.out.println(e+" receiver take exception");
		}
	}
}