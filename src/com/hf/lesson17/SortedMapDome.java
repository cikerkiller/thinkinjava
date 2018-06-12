package com.hf.lesson17;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapDome {
	public static void main(String[] args) {
		SortedMap<Integer, String> map = new TreeMap<>(new CountingMapData(10));
		System.out.println(map);
		Integer low = map.firstKey();
		Integer high = map.lastKey();
		System.out.println(low);
		System.out.println(high);
		
		Iterator<Integer> iterator = map.keySet().iterator();
		for(int i=0;i<=6;i++) {
			if(i==3) 
				low=iterator.next();
			if(i==6) 
				high=iterator.next();
			else 
				iterator.next();
		}
		System.out.println(low);
		System.out.println(high);
		System.out.println(map.subMap(low, high));
		System.out.println(map.headMap(low));
		System.out.println(map.tailMap(high));
	}
}
