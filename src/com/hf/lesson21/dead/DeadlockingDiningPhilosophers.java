package com.hf.lesson21.dead;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 死锁
public class DeadlockingDiningPhilosophers {
	public static void main(String[] args) throws Exception
	{
		args = new String[] {"0","5","timeout"};
		// 思考的时间，当其越小的时候越容易发生死锁，即都想用左边的筷子，结果都在等别人用完，然后就发生死锁
		int popher = Integer.parseInt(args[0]);
		int size = Integer.parseInt(args[1]);
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for(int i=0;i<size;i++) {
			sticks[i] = new Chopstick();
		}
		for(int i=0;i<size;i++) {
//			exec.execute(new Philosopher(sticks[i], sticks[(i+1) % size], i, popher));
			
			// 最后一个先拿起和放下左边的筷子，使死锁不在发生
			if(i<(size-1)) {
				exec.execute(new Philosopher(sticks[i], sticks[i+1], i, popher));
			}else {
				exec.execute(new Philosopher(sticks[0], sticks[i], i, popher));
			}
			
		}
		if(args.length ==3 && args[2].equals("timeout")) {
			TimeUnit.SECONDS.sleep(5);
		}else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
