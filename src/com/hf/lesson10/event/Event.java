package com.hf.lesson10.event;

public abstract class Event {
	private long eventTime;// 触发事件的时间
	protected final long delayTime;// 延迟时间
	public Event(long delayTime) {
		this.delayTime=delayTime;
		start();// 当希望运行Event时，构造器会捕获时间，即设定一个触发时间   
	}
	public void start() {
		// 触发时间 = 系统时间 + 延迟时间
		eventTime=System.nanoTime()+delayTime;
	}
	public boolean ready() {
		// 当系统时间 大于等于 设定的触发时间时 表示已经准备好了
		return System.nanoTime()>=eventTime;
	}
	public abstract void action();
}
