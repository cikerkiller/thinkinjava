package com.hf.lesson17;

import java.util.LinkedList;
// 使用组合创建Deque（双向队列）
public class Deque<T> {
	private LinkedList<T> list = new LinkedList<>();
	
	public void addFirst(T e) {
		list.addFirst(e);
	}
	
	public void addLast(T e) {
		list.addLast(e);
	}
	
	public T getFirst() {
		return list.getFirst();
	}
	
	public T getLast() {
		return list.getLast();
	}
	
	public T removeFirst() {
		return list.removeFirst();
	}
	
	public T removeLast() {
		return list.removeLast();
	}
	
	public int size() {
		return list.size();
	}
	
	public String toString() {
		return list.toString();
	}
}
