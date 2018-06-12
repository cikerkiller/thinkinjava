package com.hf.lesson15;

public class ComparablePet implements Comparable<ComparablePet> {

	@Override
	public int compareTo(ComparablePet o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
// won`t compile 基类劫持了接口
// class Cat extends ComparablePet implements Comparable<Cat>{
//
// }