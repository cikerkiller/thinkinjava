package com.hf.lesson11;

import java.util.LinkedList;

/**
 * 栈的实现：后进先出LIFO,叠加栈,最后压入栈的元素第一个弹出栈
 * @author ciker
 * @desc   
 *
 */
public class Stack<T> {
	private LinkedList<T> storage = new LinkedList<>();
	public void push(T v) {
		storage.addFirst(v);
	}
	
	/**
	 * 得到栈顶元素
	 * @return
	 */
	public T peek() {
		return storage.getFirst();
	}
	
	/**
	 * 抛出最前面的
	 * @return
	 */
	public T pop() {
		return storage.removeFirst();
	}
	public boolean empty() {
		return storage.isEmpty();
	}
	
	public String toString() {
		return storage.toString();
	}
}
