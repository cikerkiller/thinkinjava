package com.hf.lesson16;

import com.hf.lesson15.Generator;

public class GeneratorTest {
	public static int size = 5;
	public static void test(Class<?> surroundingClass) {
		//getClasses() 返回类定义的公共的内部类,以及从父类、父接口那里继承来的内部类
		for(Class<?> type:surroundingClass.getClasses()) {
			System.out.println(type.getSimpleName()+": ");
			try {
				Generator<?> g = (Generator<?>)type.newInstance();
				for(int i=0;i<size;i++) {
					System.out.print(g.next()+" ");
				}
				System.out.println();
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	public static void main(String[] args) {
//		test(CountingGenerator.class);
		test(RandomGenertor.class);
	}
}
