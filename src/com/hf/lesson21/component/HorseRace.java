package com.hf.lesson21.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 仿真赛马
 * @author ciker
 * @desc   
 *
 */
// CyclicBarrier 循环屏障  给定的栅栏是一个runnable
public class HorseRace {
	
	static final int FINISH_LINE = 75;// 终点，75步
	private List<Horse> horses = new ArrayList<>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	public HorseRace(int nHorses, final int pause) {
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				for(int i=0;i<FINISH_LINE;i++) {
					s.append("=");
				}
				System.out.println(s);
				for(Horse horse : horses) {
					System.out.println(horse.tracks());
				}
				for(Horse horse : horses) {
					// 当任意一匹马到达终点就立刻停止
					if(horse.getStrides() >= FINISH_LINE) {
						System.out.println(horse+" won!");
						exec.shutdownNow();
						return;
					}
				}
				
				try {
					// 打印很快，此处为了慢一点
					TimeUnit.MILLISECONDS.sleep(pause);
				}catch(InterruptedException e) {
					System.out.println("barrier-action sleep interrupted");
				}
			}
		});
		for(int i=0;i<nHorses;i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}
	
	public static void main(String[] args) {
		int nHorses = 7;
		int pause = 300;
		if(args.length>0) {
			int n = Integer.parseInt(args[0]);
			nHorses = n > 0 ? n : nHorses;
		}
		
		if(args.length>1) {
			int p = Integer.parseInt(args[1]);
			pause = p > -1 ? p : pause;
		}
		new HorseRace(nHorses, pause);
	
	}
}
// 马匹
class Horse implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;
	private static Random rand = new Random(47);
	private static CyclicBarrier barrier;
	public Horse(CyclicBarrier b) {
		barrier = b;
	}
	
	public synchronized int getStrides() {
		return strides;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// 每一次循环给定当前马匹随机走的步数
				synchronized (this) {
					strides += rand.nextInt(3);
				}
				// 每一匹马都在此等待Barrier打印轨迹
				barrier.await();
			}
		}catch(InterruptedException e ) {
			
		}catch(BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
	}
	public String toString() {
		return "Horse "+id+" ";
	}
	
	// 打印轨迹
	public String tracks() {
		StringBuilder sbu = new StringBuilder();
		for(int i=0;i<getStrides();i++) {
			sbu.append("*");
		}
		sbu.append(id);
		return sbu.toString();
	}
}


