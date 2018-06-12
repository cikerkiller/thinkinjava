package com.hf.lesson11;
/**
 * 容器的打印
 * @author ciker
 * @desc   
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PrintingContainers {
	static Collection fill(Collection<String> collection) {
		collection.add("sdas");
		collection.add("dsa");
		collection.add("fas");
		collection.add("afasf");
		collection.add("wqsa");
		return collection;
	}
	
	static Map fill(Map<String, String> map) {
		map.put("sda", "das");
		map.put("fq", "sa");
		map.put("fsfa", "fasf");
		map.put("sas", "fw");
		map.put("faw", "sqasf");
		return map;
	}
	
	public static void main(String[] args) {
		// AbstractCollection与AbstractMap都有实现toString()
		System.out.println(fill(new ArrayList<String>()));// 按照插入的顺序
		System.out.println(fill(new LinkedList<String>()));// 按照插入的顺序
		System.out.println(fill(new HashSet<String>()));// 最快的元素获取方式
		System.out.println(fill(new TreeSet<String>()));// 按照比较结果的升序保存对象
		System.out.println(fill(new LinkedHashSet<String>()));// 按照添加的顺序保存对象
		System.out.println(fill(new HashMap<String,String>()));// 最快查询速度
		System.out.println(fill(new TreeMap<String,String>()));// 按照比较结果升序保存键
		System.out.println(fill(new LinkedHashMap<String,String>()));// 按照添加的顺序保存对象，并保留了hashmap的查询速度
		
	}
	
	
}
