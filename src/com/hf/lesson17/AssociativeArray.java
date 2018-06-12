package com.hf.lesson17;

import java.util.HashSet;
import java.util.Set;

// 关联数组
public class AssociativeArray<K,V> {
	
	private Object[][] pairs;
	
	private int index;
	// 保证key值唯一
	private Set<K> keySet = new HashSet<>();
	
	public AssociativeArray(int length) {
		pairs = new Object[length][2];
	}
	
	public void put(K key,V value) {
		if(index > pairs.length) {
			throw new ArrayIndexOutOfBoundsException();// 数组越界异常
		}
		int size = keySet.size();
		keySet.add(key);
		if(size < keySet.size()) {
			pairs[index++] = new Object[] {key,value};
			
		}else if(size == keySet.size()) {
			// 保证key值重复的情况下最后插入的数据覆盖之前的数据
			pairs[getIndex(key)][1] = value;
		}
	}
	 
	// 糟糕的算法 
	public int getIndex(K key) {
		for(int i=0;i<index;i++) {
			if(key.equals(pairs[i][0])) {
				return i;
			}
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public V get(K key) {
		for(int i=0;i<index;i++) {
			if(key.equals(pairs[i][0])) {
				return (V)pairs[i][1];
			}
		}
		return null;
	}
	
	public String toString() {
		StringBuilder sbu = new StringBuilder("{ ");
		for(int i=0;i<index;i++) {
			sbu.append(pairs[i][0]);
			sbu.append(" = ");
			sbu.append(pairs[i][1]);
			if(i!=index-1) {
				sbu.append(", ");
			}
		}
		
		sbu.append(" }");
		return sbu.toString();
	}
	
	public static void main(String[] args) {
		AssociativeArray<String, String> map = new AssociativeArray<>(5);
//		Map<String, String> map = new LinkedHashMap<>();
//		Map<String, String> map = new HashMap<>();
//		Map<String, String> map = new TreeMap<>();
		map.put("sky", "blue");
		map.put("grass", "green");
		map.put("ocean", "dancing");
		map.put("sky1", "blue1");
		System.out.println(map);
	}
}
