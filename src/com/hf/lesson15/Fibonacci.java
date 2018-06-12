package com.hf.lesson15;

import java.util.Iterator;

/**
 * 裴波拉契数列
 * @author ciker
 * @desc   
 * 直接实现Iterable 以及使用适配器
 */
public class Fibonacci implements Generator<Integer>,Iterable<Integer>{
	private int count = 0;
	@Override
	public Integer next() {
		return fil(count++);
	}
	private int size;
	public Fibonacci() {} 
	public Fibonacci(int size) {this.size=size;} 
	
	public Integer fil(int i) {
		if(i<2) {
			return 1;
		}else {
			return fil(i-2)+fil(i-1);
		}
	}
	
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		for(int i=0;i<10;i++) {
			System.out.println(f.next());
		}
		
		for(Integer i:new IterableFibonacci2(10)) {
			System.out.println(i);
		}
		
	}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int count = size;
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Integer next() {
				count--;
				return Fibonacci.this.next();
			}
			
			@Override
			public boolean hasNext() {
				return count>0;
			}
		};
	}
}

/**
 * 通过继承实现适配器
 * @author ciker
 * @desc   
 *
 */
class IterableFibonacci extends Fibonacci implements Iterable<Integer>{
	
	private int size;
	IterableFibonacci(int size){this.size=size;}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int count = size;
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Integer next() {
				count--;
				return IterableFibonacci.this.next();
			}
			
			@Override
			public boolean hasNext() {
				return count>0;
			}
		};
	}
}

/**
 * 通过组合实现适配器
 * @author ciker
 * @desc   
 *
 */
class IterableFibonacci2 implements Iterable<Integer>{
	
	private int size;
	private Fibonacci fi =new Fibonacci();
	IterableFibonacci2(int size){this.size=size;}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int count = size;
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Integer next() {
				count--;
				return fi.next();
			}
			
			@Override
			public boolean hasNext() {
				return count>0;
			}
		};
	}
}

