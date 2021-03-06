package com.hf.lesson21;

import java.util.concurrent.atomic.AtomicInteger;

// 使用原子性变量类
public class AtomicEvenGenerator extends IntGenerator{
	private AtomicInteger i = new AtomicInteger(0);
	@Override
	public int next() {
		return i.addAndGet(2);
	}
	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}

}
