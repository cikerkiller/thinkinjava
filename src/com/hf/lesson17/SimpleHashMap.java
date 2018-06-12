package com.hf.lesson17;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import com.hf.lesson17.SlowMap.MapEntry;

@SuppressWarnings("unchecked")
public class SimpleHashMap<K,V> extends AbstractMap<K, V> {
	
	static final int SIZE = 997;
	
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	{
		buckets = new LinkedList[SIZE];
	}
	public V put(K key,V value) {
//		buckets = new LinkedList[size1];// 猜测是运行线程初始化问题，当运行到这个方法时，buckets的大小为0，不明原因
		V oldValue = null;
		boolean c = buckets ==null;
		int h = buckets.length;
		int index = keyVal(key.hashCode());// 用键经过特定的算法生成的数字作为下标
		if(buckets[index] == null) {// 当此下标的list为null时，说明未散列到此，初始化一个list以便后面添加数据
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];// 数组不直接保存值，保存值的list
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		// 遍历当前下标的列表，若有key与当前的key相同则替换旧值,
		// 因为不是线性查询整个list数组的list，而是直接跳到数组的某个位置，只对很少的元素进行比较
		while(it.hasNext()) {
			MapEntry<K, V> ipair = it.next();
			if(pair.getKey().equals(key)) {
				oldValue = ipair.getValue();
				it.set(pair);// 为了处理冲突，使用linkedlist添加到末尾
				found = true;
				break;
			}
		}
		// 当没有在当前生成的下标中找到值的列表则直接保存
		if(!found) {
			buckets[index].add(pair);
		}
		return oldValue;
	}
	
	public V get(Object key) {
		int index = keyVal(key.hashCode());// 与set一样使用相同的算法计算索引，保证可以计算出相同的位置
		if(buckets[index] == null) {
			return null;
		}
		for(MapEntry<K, V> ipair : buckets[index]) {
			if(ipair.getKey().equals(key)) {
				return ipair.getValue();
			}
		}
		return null;
	}
	
	private int keyVal(int keyHash) {
		return Math.abs(keyHash) % SIZE;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<>();
		for(LinkedList<MapEntry<K, V>> bucket : buckets) {
			if(bucket == null) {
				continue;
			}
			for(MapEntry<K, V> mpair : bucket) {
				set.add(mpair);// 遍历添加到set中，保证K,V键值对唯一
			}
		}
		return set;
	}

	@Override
	public boolean isEmpty() {
		return entrySet().size() == 0;
	}

	@Override
	public void clear() {
		buckets = new LinkedList[SIZE];
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String, String> m = new SimpleHashMap<>();
		System.out.println("============1");
		m.putAll(Countries.capitals(25));
		System.out.println(m);
		System.out.println(m.get("文莱"));
		System.out.println(m.entrySet());
		m.clear();
		System.out.println(m.isEmpty());
	}
}
