package com.hf.lesson21;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.hf.lesson15.Fibonacci;

public class ThreadVariations {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		new InnerThread1("InnerThread1");
//		new InnerRunnable2("InnerRunnable2");
//		new InnerRunnable1("InnerRunnable1");
//		new InnerThread2("InnerThread2");
//		new ThreadMethod("ThreadMethod").runTask();
		ThreadMethod1 method1 = new ThreadMethod1();
		System.out.println(method1.runTask(9).get());
	}
}
// 普通内部类
class InnerThread1 {
	private int countDown = 5;
	private Inner inner;
	private class Inner extends Thread {
		Inner(String name){
			super(name);
			start();
		}
		public void run() {
			try {
				while(true) {
					System.out.print(this);
					if(--countDown == 0) {return;}
					sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public String toString() {
			return getName() + "："+countDown+"\n";
		}
	}
	
	public InnerThread1(String name) {
		inner = new Inner(name);
	}
}
// 使用匿名内部类
class InnerThread2 {
	private int countDown = 5;
	private Thread t;
	public InnerThread2(String name) {
		t = new Thread(name) {

			@Override
			public void run() {
				try {
					while(true) {
						System.out.print(this);
						if(--countDown == 0) {return;}
						sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			public String toString() {
				return getName() + "："+countDown+"\n";
			}
		};
		t.start();
	}
}
// 使用给定名字的实现runnable的内部类
class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;
	private class Inner implements Runnable {
		Thread t;
		public Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}
		@Override
		public void run() {
			try {
				while(true) {
					System.out.print(this);
					if(--countDown == 0) {return;}
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public String toString() {
			return t.getName() + "："+countDown+"\n";
		}
	}
	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}

// 使用给定名字的匿名内部类
class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;
	InnerRunnable2(String name){
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						System.out.print(this);
						if(--countDown == 0) {return;}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			public String toString() {
				return t.getName() + "："+countDown+"\n";
			}
		},name);
		t.start();
	}
}
// 在方法中开启线程
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;
	public ThreadMethod(String name) {
		this.name = name;
	}
	public void runTask() {
		if(t == null) {
			t = new Thread(name) {

				@Override
				public void run() {
					try {
						while(true) {
							System.out.print(this);
							if(--countDown == 0) {return;}
							sleep(10);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				public String toString() {
					return getName() + "："+countDown+"\n";
				}
			};
			t.start();
		}
	}
}
// 练习10
class ThreadMethod1 {
	ExecutorService pool = Executors.newCachedThreadPool();
	public Future<String> runTask(final int n) {
		Callable<String> task = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return new Fibonacci(n).all();
			}
		};
		Future<String> future = pool.submit(task);
		pool.shutdown();
		return future;
	}
}

