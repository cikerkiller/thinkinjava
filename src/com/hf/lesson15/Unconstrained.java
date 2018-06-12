package com.hf.lesson15;

public class Unconstrained {
	public static void main(String[] args) {
		BasicOther b = new BasicOther(), b2 = new BasicOther();
		b.set(new BasicOther());
		b.get();
		b.f();
	}
}

class Other {
}

class BasicOther<T extends Other> extends BasicHolder<Other> {
}

class BasicHolder<T> {
	T element;

	void set(T arg) {
		this.element = arg;
	}

	T get() {
		return element;
	}

	void f() {
		System.out.println("this:" + getClass().getSimpleName());
		System.out.println("element:" + element.getClass().getSimpleName());
	}
}
