package com.hf.lesson15;

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovariance {
	public static void main(String[] args) {
		List<? extends Fruit> list = new ArrayList<Apple>();
		// error
		// list.add(new Apple());
		// list.add(new Fruit());
		// list.add(new Orange());
		list.add(null);
		// Fruit fruit = list.get(0);

		Number[] n = new Number[10];
		n[0] = new Integer(0);// 数组协变
		List<? extends Number> list1 = new ArrayList<>();
		// list1.add(0, new Integer(0));// error

	}
}
