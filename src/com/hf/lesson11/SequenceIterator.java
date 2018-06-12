package com.hf.lesson11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器修改 持有对象 练习1
 * @author ciker
 * @desc   
 *
 */
public class SequenceIterator {
	private List<Object> items;
	public SequenceIterator() {items=new ArrayList<>();}
	public  void add(Object x) {
		items.add(x);
	}
	/**
	 * 迭代器设计模式
	 * @author ciker
	 * @desc   
	 *
	 */
	private class SequenceSelector implements Iterator<Object>{
		private int i=0;

		@Override
		public boolean hasNext() {
			return i<items.size();
		}

		@Override
		public Object next() {
			Object o=items.get(i);
			if(i<items.size()) {
				i++;
			}
			return o;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
	public Iterator<Object> selector() {
		return new SequenceSelector();
	}
	
	/**
	 * 反向遍历序列选择器
	 * @return
	 */
	public Iterator<Object> reverseSelector() {
		return new Iterator<Object>() {
			private int i=items.size()-1;
			@Override
			public boolean hasNext() {
				return i>=0;
			}
			@Override
			public Object next() {
				return items.get(i--);
			}
			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public static void main(String[] args) {
		SequenceIterator sequence=new SequenceIterator();
		SequenceSelector selector1=sequence.new SequenceSelector();// 正序
		for(int i=0;i<12;i++) {
			// 增加的个数只能为10
			sequence.add(new Test("s"+i));
		}
		while(selector1.hasNext()) {
			System.out.println(selector1.next());
		}
		System.out.println("====================");
		
		Iterator<Object> selector=sequence.reverseSelector();// 倒序
		while(selector.hasNext()) {
			System.out.println(selector.next());
		}
	}
}


class Test1{
	private String show="sda";
	Test1(String s){
		this.show=s;
	}
	public String toString() {
		return show;
	}
}