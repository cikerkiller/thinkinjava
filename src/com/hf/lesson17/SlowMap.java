package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlowMap<K,V>  implements Map<K, V>{
	private List<K> keys = new ArrayList<>();
	private List<V> values = new ArrayList<>();
	
	public V put(K key, V value) {
		V oldValue = get(key);
		if(!keys.contains(key)) {
			keys.add(key);
			values.add(value);
		}else {
			values.set(keys.indexOf(key), value);
		}
		
		return oldValue;
	}
	
	public V get(Object key) {
		if(!keys.contains(key)) {
			return null;
		}
		return values.get(keys.indexOf(key));
	}
	
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		while(ki.hasNext()) {
			set.add(new MapEntry<K,V>(ki.next(), vi.next()));
		}
		return set;
	}
	
	
	
	@Override
	public String toString() {
		return entrySet().toString().replace("[", "{").replace("]", "}");
	}

	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}


	public static class MapEntry<K,V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		public MapEntry(K key,V value) {
			this.key=key;
			this.value=value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V v) {
			V result = value;
			this.value = v;
			return result;
		}

		@Override
		public int hashCode() {
			return (key == null ? 0 : key.hashCode()^(value == null ? 0 : value.hashCode()));
		}

		@Override
		public boolean equals(Object obj) {
			// instanceof 判断隐含obj null判断，若为null时则返回false
			if(!(obj instanceof MapEntry)) {
				return false;
			}
			@SuppressWarnings("unchecked")
			MapEntry<K,V> me = (MapEntry<K,V>)obj;
			return (key == null ? me.getKey() == null : key.equals(me.getKey())) 
					&& (value == null ? me.getValue() == null : value.equals(me.getValue()));
		}

		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new SlowMap<>();
		map.putAll(Countries.capitals(15));
		System.out.println(map);
		System.out.println(map.containsKey("中国"));
		System.out.println(map.entrySet());
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public boolean isEmpty() {
		return keys.isEmpty() && values.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	@Override
	public V remove(Object key) {
		int i = keys.indexOf(key);
		if(keys.remove(key)) {
			return values.remove(i);
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		Set<? extends K> keySet = m.keySet();
		for(K k : keySet) {
			put(k, m.get(k));
		}
		
	}

	@Override
	public Set<K> keySet() {
		return new HashSet<>(keys);
	}

	@Override
	public Collection<V> values() {
		return values;
	}
}
