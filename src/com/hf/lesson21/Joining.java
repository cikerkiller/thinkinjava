package com.hf.lesson21;

// 在一个线程中插入另一个线程
public class Joining {
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("sleepy",1500);
		Sleeper grumpy = new Sleeper("grumpy",1500);
		Joiner dopey = new Joiner("dopey", sleepy);
		Joiner doc = new Joiner("doc", grumpy);
//		grumpy.interrupt();
	}
}
class Sleeper extends Thread {
	private int duration;
	public Sleeper(String name,int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}
	@Override
	public void run() {
		try {
			sleep(duration);
		}catch(Exception e) {
			System.out.println(getName()+" was interrupted. isInterrupted():"+isInterrupted());
			return;
		}
		System.out.println(getName()+" was awakened");
	}
	
}

class Joiner extends Thread {
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName()+" join completed");
	}
}
