package com.hf.lesson17;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

// map接口可用的操作
public class Maps {
	public static void printKeys(Map map) {
		System.out.println("Size = "+map.size()+", ");
		System.out.print("Keys : ");
		System.out.println(map.keySet());
	}
	
	public static void test(Map map) {
		System.out.println(map.getClass().getSimpleName());
		map.putAll(new CountingMapData(25));
		printKeys(map);
		System.out.print("Values : ");
		System.out.println(map.values());
		System.out.println(map);
		System.out.println("map.containsKey(11): "+map.containsKey(11));
		System.out.println("map.containsValue(\"F0\"): "+map.containsValue("F0"));
		Object key = map.keySet().iterator().next();
		System.out.println("first key : "+key);
		map.remove(key);
		printKeys(map);
		map.clear();
		System.out.println("map.isEmpty(): "+map.isEmpty());
		printKeys(map);
	}
	
	public static void main(String[] args) {
		test(new HashMap<Integer,String>());
		System.out.println("=====================================");
		test(new TreeMap<Integer,String>());
		System.out.println("=====================================");
		test(new LinkedHashMap<Integer,String>());
//		System.out.println("=====================================");
//		test(new IdentityHashMap<Integer,String>());
//		System.out.println("=====================================");
//		test(new ConcurrentHashMap<Integer,String>());
//		System.out.println("=====================================");
//		test(new WeakHashMap<Integer,String>());
//		System.out.println("=====================================");
//		test(new Properties());
//		test(new SlowMap<>());
//		System.out.println("=====================================");
//		test(new SimpleHashMap<>());
	}
}
