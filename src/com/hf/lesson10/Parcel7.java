package com.hf.lesson10;

/**
 * 匿名内部类
 * @author ciker
 * @desc   
 *
 */
public class Parcel7 {
	public Contents contents() {
		return new Contents() {
			private int i=11;
			@Override
			public int value() {
				return i;
			}
		};
	}
	public static void main(String[] args) {
		Parcel7 parcel7=new Parcel7();
		Contents contents = parcel7.contents();
		int value = contents.value();
		System.out.println(value);
		
	}
}
