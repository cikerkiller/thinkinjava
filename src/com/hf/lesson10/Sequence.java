package com.hf.lesson10;

public class Sequence {
	private Object[] items;
	private int next=0;
	public Sequence(int size) {items=new Object[size];}
	public  void add(Object x) {
		if(next<items.length) {
			items[next++]=x;
		}
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
			return i==items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if(i<items.length) {
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
			
			private int i=items.length-1;
			@Override
			public boolean end() {
				return i==0;
			}
			@Override
			public Object current() {
				return items[i];
			}
		};
	}
	
	public static void main(String[] args) {
		Sequence sequence=new Sequence(10);
		SequenceSelector selector1=sequence.new SequenceSelector();
		for(int i=0;i<12;i++) {
			// 增加的个数只能为10
			sequence.add(new Test("s"+i));
		}
		
		Selector selector=sequence.reverseSelector();
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