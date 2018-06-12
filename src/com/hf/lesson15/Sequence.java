package com.hf.lesson15;

import java.util.ArrayList;
import java.util.List;

public class Sequence<T> {
	private List<T> items;
	public Sequence() {items=new ArrayList<>();}
	public  void add(T x) {
		items.add(x);
	}
	/**
	 * 迭代器设计模式
	 * @author ciker
	 * @desc   
	 *
	 */
	private class SequenceSelector implements Selector{
		private int i=0;
		@Override
		public boolean end() {
			return i==items.size();
		}

		@Override
		public Object current() {
			return items.get(i);
		}

		@Override
		public void next() {
			if(i<items.size()) {
				i++;
			}
		}
	}
	public Selector selector() {
		return new SequenceSelector();
	}
	
	/**
	 * 反向遍历序列选择器
	 * @return
	 */
	public Selector reverseSelector() {
		return new Selector() {
			
			@Override
			public void next() {
				i--;
			}
			
			private int i=items.size()-1;
			@Override
			public boolean end() {
				return i==0;
			}
			@Override
			public Object current() {
				return items.get(i);
			}
		};
	}
	
	public static void main(String[] args) {
		Sequence<Test> sequence=new Sequence<Test>();
		//SequenceSelector selector1=sequence.new SequenceSelector();// 正序
		Selector selector1=sequence.selector();// 正序
		for(int i=0;i<12;i++) {
			// 增加的个数只能为10
			sequence.add(new Test("s"+i));
		}
		while(!selector1.end()) {
			System.out.println(selector1.current());
			selector1.next();
		}
		System.out.println("====================");
		
		Selector selector=sequence.reverseSelector();// 倒序
		while(!selector.end()) {
			System.out.println(selector.current());
			selector.next();
		}
	}
}
interface Selector{
	boolean end();
	Object current();
	void next();
}

class Test{
	private String show="sda";
	Test(String s){
		this.show=s;
	}
	public String toString() {
		return show;
	}
}