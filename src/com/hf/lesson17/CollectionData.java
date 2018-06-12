package com.hf.lesson17;

import java.util.ArrayList;

import com.hf.lesson15.Generator;

// 根据生成器来填充容器
public class CollectionData<T> extends ArrayList<T> {
	public CollectionData(Generator<T> gen, int quantity) {
		for(int i=0;i<quantity;i++) {
			add(gen.next());
		}
	}
	
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity){
		return new CollectionData<>(gen, quantity);
	}
}
