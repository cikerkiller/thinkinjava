package com.hf.lesson15;

@SuppressWarnings("hiding")
public class TwoTuple<A,B> {
	public final A first;
	public final B second;
	public TwoTuple(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}
	@Override
	public String toString() {
		return "TwoTuple [first=" + first + ", second=" + second + "]";
	}
	
}
