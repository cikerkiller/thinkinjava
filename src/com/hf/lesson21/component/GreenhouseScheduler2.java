package com.hf.lesson21.component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 模拟温室控制器
public class GreenhouseScheduler2 extends Thread{
	private volatile boolean light  = false;
	private volatile boolean water = false;
	private String thermostat = "Day";
	public synchronized String getThermostat() {
		return thermostat;
	}
	public synchronized void setThermostat(String value) {
		this.thermostat = value;
	}
	public GreenhouseScheduler2() {
		start();
	}
	
	@Override
	public void run() {
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<GreenhouseTask> delayQueue = new DelayQueue<>();
		delayQueue.put(new LightOn(500));
		delayQueue.put(new Bell(800));
		delayQueue.put(new LightOff(1560));
		delayQueue.put(new Terminate(5000,exec));
		exec.execute(new GreenhouseTaskConsumer(delayQueue));
		
	}
	
	public static void main(String[] args) {
		new GreenhouseScheduler2();
	}
	
	class LightOn extends GreenhouseTask {
		public LightOn(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Turning on light");
			light = true;
		}
	}
	class LightOff extends GreenhouseTask {
		public LightOff(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Turning off light");
			light = false;
		}
	}
	class WaterOn extends GreenhouseTask {
		public WaterOn(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Turning greenhouse water on");
			water = true;
		}
	}
	class WaterOff extends GreenhouseTask {
		public WaterOff(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Turning greenhouse water off");
			water = false;
		}
	}
	
	class ThermostatNight extends GreenhouseTask {
		public ThermostatNight(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Thermostat to night setting");
			setThermostat("Night");
		}
	}
	class ThermostatDay extends GreenhouseTask {
		public ThermostatDay(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Thermostat to Day setting");
			setThermostat("Day");
		}
	}
	
	class Bell extends GreenhouseTask {
		public Bell(int delayInMilliseconds) {
			super(delayInMilliseconds);
		}

		@Override
		public void run() {
			System.out.println("Belling!");
		}
	}
	
	class Terminate extends GreenhouseTask {
		private ExecutorService exec;
		public Terminate(int delayInMilliseconds,ExecutorService exec) {
			super(delayInMilliseconds);
			this.exec = exec;
		}

		@Override
		public void run() {
			System.out.println("Terminating");
			exec.shutdownNow();
		}
	}
}

class  GreenhouseTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	public GreenhouseTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;// 延迟毫秒数
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
  	}
	
	@Override
	public int compareTo(Delayed o) {
		GreenhouseTask that = (GreenhouseTask)o;
		if(trigger < that.trigger) {
			return -1;
		}else if(trigger > that.trigger) {
			return 1;
		}else {
			return 0;
		}
	}
	/**
	 * 告知延迟时间还有多长时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		System.out.println(this+" ");
	}
	public String toString() {
		return String.format("[%1$-4d]", delta) + " task " +id;
	}
}
class GreenhouseTaskConsumer implements Runnable {
	private DelayQueue<GreenhouseTask> q;//GreenhouseTask 存放实现了Delayed的对象，其中对象只能在其到期时才能从队列中取走
	public GreenhouseTaskConsumer(DelayQueue<GreenhouseTask> q) {
		this.q = q;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				q.take().run();
			}
		}catch(InterruptedException e) {
			
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
}