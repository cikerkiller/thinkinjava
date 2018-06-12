package com.hf.lesson10;
/**
 * 带参数的匿名内部类
 * @author ciker
 * @desc   
 *
 */
public class Parcel8 {
	public Wrapping wrapping(int x) {
		return new Wrapping(x) {// 这个匿名内部类继承了Wrapping 
			@Override
			public int value() {
				return super.value()*47;
			}
		};
	}
}
