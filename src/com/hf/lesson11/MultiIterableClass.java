package com.hf.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
/**
 * 适配器方法惯用法
 * @author ciker
 * @desc   
 *
 */
public class MultiIterableClass extends IterableClass{
	// 反向迭代
	public Iterable<String> reversed(){
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					int current = words.length-1;
					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
					
					@Override
					public String next() {
						return words[current--];
					}
					
					@Override
					public boolean hasNext() {
						return current>-1;
					}
				};
			}
		};
	}
	
	public Iterable<String> randomized(){
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				List<String> shuffled = new ArrayList<>(Arrays.asList(words));
				// 把顺序打混
				Collections.shuffle(shuffled, new Random(47));
				return shuffled.iterator();
			}
		};
	}
	public static void main(String[] args) {
		MultiIterableClass mic = new MultiIterableClass();
		for(String s:mic) {
			System.out.print(s+" ");
		}
		System.out.println("\r\n=====================");
		for(String s:mic.reversed()) {
			System.out.print(s+" ");
		}
		System.out.println("\r\n=====================");
		for(String s:mic.randomized()) {
			System.out.print(s+" ");
		}
	}
	
}
