package com.hf.lesson15;

import java.util.ArrayList;
import java.util.List;

public class CaptureConversion {
	static <T> void f1(Holder<T> holder) {
		T t = holder.get();
		System.out.println(t.getClass().getSimpleName());
	}

	static void f2(Holder<?> holder) {
		f1(holder);
	}

	static void f3(Holder<List<Holder<?>>> ho) {
		List<?> list = ho.get();
		// list.add(new Object());
		// list.set(0, new Object());
		System.out.println("====" + list.get(0).getClass().getSimpleName());
		ho.set(new ArrayList<Holder<?>>(1));
	}

	// @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Holder raw = new Holder<Integer>(1);
		// f1(raw);// 产生警告
		// f2(raw);// no warnings
		//
		// Holder rawBasic = new Holder();
		// rawBasic.set(new Object());// warning
		//
		// f2(rawBasic);// no warnings
		//
		// Holder<?> wildcarded = new Holder<Double>(1.0);
		// f2(wildcarded);
		Holder<List<Holder<?>>> ho = new Holder<>();
		List<Holder<?>> list = new ArrayList<Holder<?>>();
		// list.add(new Object());// 编译不通过
		// list.set(1, new CaptureConversion());// 编译不通过
		list.add(new Holder<>());
		ho.set(list);
		f3(ho);
	}
}
