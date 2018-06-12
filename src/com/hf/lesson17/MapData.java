package com.hf.lesson17;

import java.util.LinkedHashMap;

import com.hf.lesson15.Generator;

public class MapData<K,V> extends LinkedHashMap<K, V> {
	public MapData(Generator<Pair<K,V>> gen,int quantity) {
		for(int i=0;i<quantity;i++) {
			Pair<K,V> pair=gen.next();
			K key = pair.key;
			V value = pair.value;
			put(key, value);
		}
	}
	
	public MapData(Generator<K> genK,Generator<V> genV,int quantity) {
		for(int i = 0; i < quantity; i++) {
			K key = genK.next();
			V value = genV.next();
			put(key, value);
		}
	}
	
	public MapData(Generator<K> genK,V value,int quantity) {
		for(int i = 0; i < quantity; i++) {
			put(genK.next(), value);
		}
	}
	
	
	public MapData(Iterable<K> genK,Generator<V> genV) {
		for(K key : genK) {
			put(key, genV.next());
		}
	}
	
	public MapData(Iterable<K> genK,V value) {
		for(K key : genK) {
			put(key, value);
		}
	}
	
	public static <K,V> MapData<K,V> map(Generator<Pair<K,V>> gen,int quantity){
		return new MapData<K,V>(gen, quantity);
	}
	public static <K,V> MapData<K,V> map(Generator<K> genK,Generator<V> genV,int quantity){
		return new MapData<K,V>(genK, genV, quantity);
	}
	public static <K,V> MapData<K,V> map(Generator<K> genK,V value,int quantity){
		return new MapData<K, V>(genK, value, quantity);
	}
	public static <K,V> MapData<K,V> map(Iterable<K> genK,Generator<V> genV){
		return new MapData<>(genK, genV);
	}
	public static <K,V> MapData<K,V> map(Iterable<K> genK,V value){
		return new MapData<>(genK, value);
	}
}
