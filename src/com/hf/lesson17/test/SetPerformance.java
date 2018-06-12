package com.hf.lesson17.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// set性能测试
public class SetPerformance {
	static List<Test<Set<Integer>>> tests = new ArrayList<>();
	static {
		tests.add(new Test<Set<Integer>>("add") {
			
			@Override
			int test(Set<Integer> container, TestParam tp) {
				int loops =tp.loops;
				int size = tp.size;
				for(int i=0;i<loops;i++) {
					container.clear();
					for(int j=0;j<size;j++) {
						container.add(j);
					}
				}
				return loops * size;// 循环次数
			}
		});
		
		
		tests.add(new Test<Set<Integer>>("contains") {
			
			@Override
			int test(Set<Integer> container, TestParam tp) {
				int loops =tp.loops;
				int size = tp.size * 2;
				for(int i=0;i<loops;i++) {
					for(int j=0;j<size;j++) {
						container.contains(j);
					}
				}
				return loops * size;// 循环次数
			}
		});
		
		
		tests.add(new Test<Set<Integer>>("iterate") {
			
			@Override
			int test(Set<Integer> container, TestParam tp) {
				int loops =tp.loops * 10;
				for(int i=0;i<loops;i++) {
					Iterator<Integer> it = container.iterator();
					while(it.hasNext()) {
						it.next();
					}
				}
				return loops * container.size();// 循环次数
			}
		});
	}
	
	public static void main(String[] args) {
		Tester.run(new TreeSet<Integer>(), tests);// 支持排序
		Tester.run(new HashSet<Integer>(), tests);// 添加与查询速度比较快
		Tester.run(new LinkedHashSet<Integer>(), tests);// 保持插入顺序
	}
}
