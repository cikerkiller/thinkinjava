package com.hf.lesson21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hf.lesson15.Fibonacci;

// 练习五
public class TestCallable1 implements Callable<String>{
	private int n;
	public TestCallable1(int n) {
		this.n = n;
	}
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Future<String>> list = new ArrayList<>();
		for(int i=1;i<10;i++) {
			list.add(threadPool.submit(new TestCallable1(i)));
		}
		
		for(Future<String> fs : list) {
			try {
					System.out.println(fs.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}finally {
					threadPool.shutdown();
				}
			}
	}
	@Override
	public String call() throws Exception {
		Fibonacci f = new Fibonacci(n);
		return f.all();
	}

}
