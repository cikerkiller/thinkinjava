package com.hf.lesson21.performance;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 乐观加锁 假设不出现冲突，不加锁，但是为了保证数据的一致性，数据通常有一个版本号，乐观锁通过版本号比较，看数据是否被修改过，如果不一致则重试，或者放弃修改
public class FastSimulation {
	static final int N_ELEMENTS = 100000;
	static final int N_GENES = 30;
	static final int N_EVOLVERS = 50;
	static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
	
	static Random rand = new Random(47);
	static class Evolver implements Runnable {
		public void run() {
			while(!Thread.interrupted()) {
				int element = rand.nextInt(N_ELEMENTS);
				for(int i=0;i<N_GENES;i++) {
					int previous = element - 1;
					if(previous < 0) {
						previous = N_ELEMENTS - 1;
					}
					int next = element + 1;
					if(next >= N_ELEMENTS) {
						next = 0;
					}
					int oldValue = GRID[element][i].get();
					int newValue = oldValue + GRID[previous][i].get() + GRID[next][i].get();
					
					newValue /= 3;
					if(!GRID[element][i].compareAndSet(oldValue, newValue)) {
						System.out.println("Old value changed from "+oldValue);
					}
					
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<N_ELEMENTS;i++) {
			for(int j=0;j<N_GENES;j++) {
				GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
			}
		}
		
		for(int i=0;i<N_EVOLVERS;i++) {
			exec.execute(new Evolver());
		}
		
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
		
		
	}
	
}
