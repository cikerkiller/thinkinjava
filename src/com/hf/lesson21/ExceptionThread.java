package com.hf.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{

	@Override
	public void run() {
		throw new RuntimeException();
	}
	public static void main(String[] args) {
		try {
			ExecutorService pool = Executors.newCachedThreadPool();
			pool.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("e:"+e);// 未捕获到异常
			e.printStackTrace();
		}
	}

}
