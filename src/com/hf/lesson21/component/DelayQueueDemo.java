package com.hf.lesson21.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 延迟队列，根据延迟时间加载，延迟时间长的最后加载,延迟时间不到不会出队列
public class DelayQueueDemo {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<>();
		
		for(int i=0;i<20;i++) {
			queue.put(new DelayedTask(rand.nextInt(50000)));
		}
		// 根据延迟时间设为最大延迟加载，所有其最后运行,然后关闭掉线程
		queue.put(new DelayedTask.EndSentinel(50000, exec));
		
		exec.execute(new DelayedTaskConsumer(queue));
		
	}
}

class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<>();
	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;// 延迟毫秒数
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}
	
	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask)o;
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
	public String summary() {
		return "(" + id + ":" +delta + ")";
	}
	
	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;
		public EndSentinel(int delay,ExecutorService exec) {
			super(delay);
			this.exec = exec;
		}
		public void run() {
			for(DelayedTask pt : sequence) {
				// 打印排序信息 从0~20
				System.out.println(pt.summary()+ " ");
			}
			System.out.println();
			System.out.println(this+" Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;//DelayQueue 存放实现了Delayed的对象，其中对象只能在其到期时才能从队列中取走
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
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
