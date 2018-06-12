package com.hf.lesson15.erased;

/**
 * 边界 T extends T1 发生动作的地方
 * 
 * @author ciker
 * @desc
 *
 */
public class Erased<T extends T1> {

	private T obj;

	public Erased(T obj) {
		this.obj = obj;
	}

	public T getT() {
		return obj;
	}

	public void gg() {
		obj.f();
	}

	public static void main(String[] args) {
		Erased<T1> erased = new Erased<T1>(new TT());
		erased.gg();
	}
}

interface T1 {
	void f();
}

class TT implements T1 {
	public void g() {
		System.out.println("gg");
	}

	@Override
	public void f() {
		g();
	}
}