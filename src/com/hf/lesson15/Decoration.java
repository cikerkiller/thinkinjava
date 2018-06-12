package com.hf.lesson15;

import java.util.Date;
/**
 * 装饰器：缺点，只能有效的工作于装饰中的一层（最后一成）
 * @author ciker
 * @desc   
 *
 */
public class Decoration {
	public static void main(String[] args) {
		TimeStamped1 t1 = new TimeStamped1(new BasicImpl());
		TimeStamped1 t2 = new TimeStamped1(new SerialNumbered1(new BasicImpl()));
//		t2.getSerialNumber(); 因为持有的是TimeStamped的对象,其没有这个方法
		
	}
}

class Decorator extends BasicImpl{
	protected Basic basic;
	public Decorator(Basic basic) {
		this.basic=basic;
	}
	@Override
	public void set(String val) {
		basic.set(val);
	}
	@Override
	public String get() {
		return basic.get();
	}
}

class TimeStamped1 extends Decorator{
	private final long timeStamp;
	public TimeStamped1(Basic basic) {
		super(basic);
		timeStamp = new Date().getTime();
	}
	public long getStamp() {
		return timeStamp;
	}
}

class SerialNumbered1 extends Decorator{
	private static long count = 1;
	private final long number = count++;
	public SerialNumbered1(Basic basic) {
		super(basic);
	}
	public long getSerialBNumber() {
		return number;
	}
}
