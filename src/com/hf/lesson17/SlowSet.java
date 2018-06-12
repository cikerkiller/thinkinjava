package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hf.lesson16.RandomGenertor;

public class SlowSet<E> implements Set<E>{
	private List<E> keys = new ArrayList<>();
	
	public SlowSet() {}
	public SlowSet(Collection<? extends E> c) {
		this();
		addAll(c);
	}
	
	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return o == null ? false : keys.indexOf(o) >= 0;
	}

	@Override
	public Iterator<E> iterator() {
		return keys.iterator();
	}

	@Override
	public Object[] toArray() {
		return keys.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return keys.toArray(a);
	}

	@Override
	public boolean add(E e) {
		if(keys.indexOf(e)<0) {
			keys.add(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		
		if(keys.indexOf(o) >= 0) {
			return keys.remove(o);
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return keys.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean isAdd = true;
		Set<E> set = new HashSet<>();
		for(E e : c) {
			if(isAdd) {
				isAdd=add(e);
				if(isAdd) {
					set.add(e);
				}
			}else {
				keys.removeAll(set);
			}
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return keys.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		List<E> list = new ArrayList<>(keys); 
		boolean isRemove = true;
		for(Object o : c) {
			if(isRemove) {
				isRemove=remove(o);
			}else {
				addAll(list);
			}
		}
		return false;
	}

	@Override
	public void clear() {
		keys.clear();
	}
	@Override
	public String toString() {
		return keys.toString();
	}
	
	public static void main(String[] args) {
		SlowSet<Integer> sl = new SlowSet<>();
		sl.addAll(CollectionData.list(new RandomGenertor.Integer(), 15));
		System.out.println(sl);
		sl = new SlowSet<>(CollectionData.list(new RandomGenertor.Integer(), 5));
		System.out.println(sl);
		sl.add(5);
		System.out.println(sl);
		sl.remove(5);
		System.out.println(sl);
		System.out.println(sl.retainAll(Arrays.asList(1,5,3,4,9258)));// 得到交集,只保留交集
		System.out.println(sl.removeAll(Arrays.asList(1,5,3,4,9258)));
		System.out.println(sl);
	}
}
