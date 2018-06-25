package com.hf.lesson21.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

// 优先级同步队列
public class PriorityBlockingQueueDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PrioritizedTaskConsumer(queue));
	}   
}
class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequence = new ArrayList<>();
	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequence.add(this);
	}
	
	@Override
	public int compareTo(PrioritizedTask o) {
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		}catch(InterruptedException e) {
			
		}
		System.out.println(this);
	}
	public String toString() {
		return String.format("[%1$-3d]", priority) +" task "+ id;
	}
	public String summary() {
		return "("+id+":"+priority+")";
	}
	
	// 结束线程
	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;
		public EndSentinel(ExecutorService exec) {
			super(-1);// 设为最低优先级
			this.exec = exec;
		}
		
		public void run() {
			int count = 0;
			for(PrioritizedTask p : sequence) {
				System.out.println(p.summary());
				if(++count % 5 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println(this+" Calling shutdownNow()");
			exec.shutdownNow();
		}
		
	}
}

class PrioritizedTaskProducer implements Runnable {
	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;
	public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
		this.queue = queue;
		this.exec = exec;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			queue.add(new PrioritizedTask(rand.nextInt(10)));
			Thread.yield();
		}
		
		try {
			for(int i=0;i<10;i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(10));
			}
			for(int i=0;i<10;i++) {
				queue.add(new PrioritizedTask(i));
			}
			queue.add(new PrioritizedTask.EndSentinel(exec));
		} catch (InterruptedException e) {
			
		}
		System.out.println("Finished PrioritizedTaskProducer");
	}
}

class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;
	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		super();
		this.q = q;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				q.take().run();
			}
		} catch (InterruptedException e) {
			
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}
	
}


