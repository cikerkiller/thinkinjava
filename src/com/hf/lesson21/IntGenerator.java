package com.hf.lesson21;

public abstract class IntGenerator {
	// volatile保证了可见性已经禁止指令重排序,使用它要保证是原子性操作
	// 1）对变量的写操作不依赖于当前值
	// 2）该变量没有包含在具有其他变量的不变式中
	private volatile boolean canceled = false;
	public abstract int next();
	public void cancel() { canceled = true; }
	public boolean isCanceled() { return canceled; }
}
