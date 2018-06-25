package com.hf.lesson21.component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.hf.lesson15.BaseGenerator;
import com.hf.lesson15.Generator;

// exchanger 是在两个任务之间交换对象的栅栏 
// 本例中生产者将装满Fat的list与消费者的空list交换，在创建的同时被消费
public class ExchangerDemo {
	static int size = 10;
	static int delay = 5;
	public static void main(String[] args) throws Exception{
		if(args.length>0) {
			size = new Integer(args[0]);
		}
		if(args.length>1) {
			delay = new Integer(args[1]);
		}
		
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> ec = new Exchanger<>();
		List<Fat> producerList = new CopyOnWriteArrayList<>();
		List<Fat> consumerList = new CopyOnWriteArrayList<>();
		
		exec.execute(new ExchangerProducer<Fat>(BaseGenerator.create(Fat.class), ec, producerList));
		exec.execute(new ExchangerConsumer<Fat>(ec, consumerList));
		
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
		
		
		
	}
}

class ExchangerProducer<T> implements Runnable {
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	public ExchangerProducer(Generator<T> generator, Exchanger<List<T>> exchanger, List<T> holder) {
		super();
		this.generator = generator;
		this.exchanger = exchanger;
		this.holder = holder;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				for(int i=0;i<ExchangerDemo.size;i++) {
					holder.add(generator.next());
				}
				holder = exchanger.exchange(holder);
				System.out.println("holder: "+holder);
			}
		} catch (InterruptedException e) {
			
		}
	}
}

class ExchangerConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
		super();
		this.exchanger = exchanger;
		this.holder = holder;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for(T x : holder) {
//					System.out.println(x);
					value = x;
					holder.remove(x);
				}
			}
		} catch (InterruptedException e) {
		}
		System.out.println("Final value : "+value);
	}
}
