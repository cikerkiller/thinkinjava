package com.hf.lesson15;

import java.util.Iterator;
import java.util.LinkedList;

public class SimpleQueue<T> implements Iterable<T> {
	private LinkedList<T> storage = new LinkedList<>();
	
	public void add(T item) {
		storage.offer(item);
	}
	
	public T get() {
		return storage.poll();
	}
	@Override
	public Iterator<T> iterator() {
		return storage.iterator();
	}

}
