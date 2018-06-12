package com.hf.lesson17;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CountingMapData extends AbstractMap<Integer, String>{
	private int size;
	private static String[]  chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
	
	public CountingMapData(int size) {
		if(size<0) {
			size = 0;
		}
		this.size=size;
	}
	
	private static class Entry implements Map.Entry<Integer, String> {
		int index;
		Entry(int index){
			this.index=index;
		}
		
		public boolean equals(Object o) {
			return Integer.valueOf(index).equals(o);
		}
		
		
		@Override
		public int hashCode() {
			return Integer.valueOf(index).hashCode();
		}

		@Override
		public Integer getKey() {
			return Integer.valueOf(index);
		}

		@Override
		public String getValue() {
			return chars[index % chars.length]+Integer.toString(index / chars.length);
		}

		@Override
		public String setValue(String value) {
			// 只读操作
			throw new UnsupportedOperationException();
		}
	}
	
		class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
		public EntrySet(int size1) {
			size = size1;
		}
		public EntrySet() {
		}
		@Override
		public Iterator<java.util.Map.Entry<Integer, String>> iterator() {
			final Entry entry = new Entry(-1);
			return new Iterator<Map.Entry<Integer,String>>() {
				
				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
				
				@Override
				public Map.Entry<Integer, String> next() {
					entry.index++;
					return entry;
				}
				
				@Override
				public boolean hasNext() {
					return entry.index<size-1;
				}

			};
		}

		@Override
		public int size() {
			return size;
		}
		
	}
	
	
	
	@Override
	public Set<Map.Entry<Integer, String>> entrySet() {
//		Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<>();
//		for(int i=0;i<size;i++) {
//			entries.add(new Entry(i));
//		}
//		return entries;
		
		return new EntrySet();// 享元设计模式
	}
	
	
	public static void main(String[] args) {
		System.out.println(new CountingMapData(60));
	}
	

}
