package com.hf.lesson15;

/**
 * 栈的实现：后进先出LIFO,叠加栈,最后压入栈的元素第一个弹出栈
 * 
 * @author ciker
 * @desc
 *
 */
public class LinkedStack<T> {
	private static class Node<U> {
		U item;
		Node<U> next;// 下一个

		Node() {
			item = null;
			next = null;
		}

		Node(U item, Node<U> next) {
			this.item = item;
			this.next = next;
		}

		boolean end() {
			return item == null && next == null;
		}
	}

	private Node<T> top = new Node<>();

	public void push(T item) {
		top = new Node<T>(item, top);// 将上一个填入到这一个里
	}

	public T pop() {
		T result = top.item;
		if (!top.end()) {
			top = top.next;
		}
		return result;
	}

	public static void main(String[] args) {
		LinkedStack<String> lss = new LinkedStack<>();
		for (String s : "Phasers on stun!".split(" ")) {
			lss.push(s);
		}
		String s;
		while ((s = lss.pop()) != null) {
			System.out.println(s);
		}
	}
}
