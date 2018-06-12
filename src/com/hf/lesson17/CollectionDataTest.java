package com.hf.lesson17;

import java.util.LinkedHashSet;

import com.hf.lesson15.Generator;

public class CollectionDataTest {
	public static void main(String[] args) {
		LinkedHashSet<Government> list = new LinkedHashSet<>(CollectionData.list(new Government(), 5));
		// 打印的顺序为插入顺序,因为linkedhashset维护的是保持了插入的顺序的链接列表
		System.out.println(list);
		
		SlowSet<Government> set = new SlowSet<Government>(CollectionData.list(new Government(), 5));
		System.out.println(set);
		
	}
}

class Government implements Generator<Government>{
	private static long counter = 0;
	private final long id = counter++;
	@Override
	public Government next() {
		return new Government();
	}
	
	public String toString() {
		return super.toString()+" "+id;
	}
	
}
