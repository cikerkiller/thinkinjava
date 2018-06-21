package com.hf.lesson21;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDome {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for(int i=0;i<10;i++) {
			results.add(threadPool.submit(new TaskWithResult(i)));
		}
		for(Future<String> fs : results) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			}finally {
				threadPool.shutdown();
			}
		}
	}
}
class TaskWithResult implements Callable<String> {
	private int id;
	public TaskWithResult(int id) {
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult "+id;
	}
	
}
