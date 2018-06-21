package com.hf.lesson21;

public class EvenGenerator extends IntGenerator{
	private int currentEvenValue = 0;
	@Override
	public synchronized int next() {
		// 加上同步后，返回的必定是个偶数   
		++currentEvenValue;// 不保证原子性,即可能被中断，当加1后另一个线程抢占运行时间，此时就变成了奇数
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
