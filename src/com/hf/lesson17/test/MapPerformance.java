package com.hf.lesson17.test;
// map性能测试

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapPerformance {
	static List<Test<Map<Integer, Integer>>> tests = new ArrayList<>();
	static {
		tests.add(new Test<Map<Integer,Integer>>("put") {
			
			@Override
			int test(Map<Integer, Integer> container, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i=0;i<loops;i++) {
					container.clear();// 此处simpleHashMap实现的clear将数组大小设为0了，然后运行出错
					for(int j=0;j<size;j++) {
						container.put(j, j);
					}
				}
				return loops * size;
			}
		});
		
		
		tests.add(new Test<Map<Integer,Integer>>("get") {
			
			@Override
			int test(Map<Integer, Integer> container, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size * 2;
				for(int i=0;i<loops;i++) {
					for(int j=0;j<size;j++) {
						container.get(j);
					}
				}
				return loops * size;
			}
		});
		
		
		tests.add(new Test<Map<Integer,Integer>>("iterate") {
			
			@Override
			int test(Map<Integer, Integer> container, TestParam tp) {
				int loops = tp.loops * 10;
				for(int i=0;i<loops;i++) {
					Iterator<Entry<Integer, Integer>> it = container.entrySet().iterator();
					while(it.hasNext()) {
						it.next();
					}
				}
				return loops * container.size();
			}
		});
	}
	
	public static void main(String[] args) {
//		Tester.run(new TreeMap<Integer,Integer>(), tests);
		Tester.run(new HashMap<Integer,Integer>(10, 0.25f), tests);  
		Tester.run(new HashMap<Integer,Integer>(10, 0.55f), tests);  
		Tester.run(new HashMap<Integer,Integer>(10, 0.75f), tests);  
//		Tester.run(new LinkedHashMap<Integer,Integer>(), tests);
//		Tester.run(new IdentityHashMap<Integer,Integer>(), tests);
//		Tester.run(new WeakHashMap<Integer,Integer>(), tests);
//		Tester.run(new java.util.Hashtable<Integer,Integer>(), tests);
//		Tester.run(new SlowMap<Integer,Integer>(), tests);// 迭代最慢
//		Tester.run(new SimpleHashMap<Integer,Integer>(), tests);
	}
}
