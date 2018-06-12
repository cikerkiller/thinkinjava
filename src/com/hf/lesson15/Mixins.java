package com.hf.lesson15;

import java.util.Date;

/**
 * 混型
 * @author ciker
 * @desc   
 *
 */
public class Mixins {
	public static void main(String[] args) {
		Mixin mixin1 =new Mixin(),mixin2=new Mixin();// 初始化的时候时间已经定下来了
		mixin1.set("string1");
		mixin2.set("string2");
		System.out.println(mixin1.get()+": "+mixin1.getSerialNumber()+"  "+mixin1.getStamp());
		try {
			Thread.sleep(100);// 此处没有效果
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mixin2.get()+": "+mixin2.getSerialNumber()+"  "+mixin2.getStamp());
	}
}
interface TimeStamped{
	long getStamp();
}

// 时间戳
class TimeStampedImpl implements TimeStamped{
	private final long timeStamp;
	
	public TimeStampedImpl() {
		super();
		this.timeStamp = new Date().getTime();
	}

	@Override
	public long getStamp() {
		return timeStamp;
	}
}

interface SerialNumbered {
	long getSerialNumber();
}

// 序列号
class SerialNumberedImpl implements SerialNumbered{
	private static long counter = 1;
	private final long serialNumber = counter++;// 每增加一个对象序列号+1
	@Override
	public long getSerialNumber() {
		return serialNumber;
	}
}

interface Basic{
	void set(String val);
	String get();
}

class BasicImpl implements Basic{
	private String value;
	@Override
	public void set(String val) {
		this.value=val;
	}

	@Override
	public String get() {
		return value;
	}
}
// 混型 使用代理
class Mixin extends BasicImpl implements TimeStamped,SerialNumbered{
	private TimeStamped TimeStamped = new TimeStampedImpl();
	private SerialNumbered serialNumber = new SerialNumberedImpl();
	@Override
	public long getSerialNumber() {
		return serialNumber.getSerialNumber();
	}

	@Override
	public long getStamp() {
		return TimeStamped.getStamp();
	}
	
}
