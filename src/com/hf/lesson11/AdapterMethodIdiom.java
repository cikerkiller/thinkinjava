package com.hf.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class AdapterMethodIdiom {
	public static void main(String[] args) {
		ReversibleArrayList<String> ral = new ReversibleArrayList<String>(Arrays.asList("to be or not to be".split(" ")));
		for(String s:ral) {
			System.out.print(s+" ");
		}
		System.out.println("\r\n=================");
		for(String s:ral.reversed()) {
			System.out.print(s+" ");
		}
	}
}
class ReversibleArrayList<T> extends ArrayList<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565139360797190287L;


	public ReversibleArrayList(Collection<T> c) {super(c);}
	
	
	/**
	 * 默认是前向迭代器，此处添加一个方法，返回一个反向迭代器
	 * @return
	 */
	public Iterable<T> reversed(){
		return new Iterable<T>(){
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int current = size()-1;
					@Override
					public boolean hasNext() {
						return current>=0;
					}

					@Override
					public T next() {
						return get(current--);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}
