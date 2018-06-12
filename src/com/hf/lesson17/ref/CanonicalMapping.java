package com.hf.lesson17.ref;

import java.util.Arrays;
import java.util.WeakHashMap;

public class CanonicalMapping {
	public static void main(String[] args) {
		int size = 100;
		Key[] keys = new Key[size];
		WeakHashMap<Key, Value> map = new WeakHashMap<>();
		for(int i=0;i<size;i++) {
			Key key = new Key(Integer.toString(i));
			Value value = new Value(Integer.toString(i));
			if(i % 3 == 0) {
				keys[i] = key;// 每隔三个键存入数组，指向这个键的普通引用不会被垃圾回收器回收
			}
			map.put(key, value);
		}
		System.gc();
		System.out.println(map);
		System.out.println(Arrays.toString(keys));
	}
}
class Element{
	private String ident;
	public Element(String id) {
		this.ident=id;
	}
	public String toString() {
		return ident;
	}
	public int hashCode() {
		return ident.hashCode();
	}
	public boolean equals(Object o) {
		return o instanceof Element && ident.equals(((Element)o).ident);
	}
	protected void finalize() {
		System.out.println("Finalizing "+getClass().getSimpleName()+" "+ident);
	}
}

class Key  extends Element{

	public Key(String id) {
		super(id);
	}
}

class Value extends Element{

	public Value(String id) {
		super(id);
	}
}