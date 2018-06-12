package com.hf.lesson15;

/**
 * 泛型之开始
 * 
 * @author ciker
 * @desc
 *
 */
public class Holder<T> {
	private T a;

	public Holder(T a) {
		this.a = a;
	}

	public Holder() {
	}

	public void set(T a) {
		this.a = a;
	}

	public T get() {
		return a;
	}

	public static void main(String[] args) {
		Holder<Automobile> h = new Holder<Automobile>(new Automobile());
		Automobile automobile = h.get();

	}
}

class Automobile {
}
