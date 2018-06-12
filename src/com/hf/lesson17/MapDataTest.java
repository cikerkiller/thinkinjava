package com.hf.lesson17;

import java.util.Iterator;

import com.hf.lesson15.Generator;
import com.hf.lesson16.RandomGenertor;

public class MapDataTest {
	public static void main(String[] args) {
		System.out.println(MapData.map(new Letters(), 5));
		System.out.println(MapData.map(new Letters(),new RandomGenertor.String()));
		System.out.println(MapData.map(new Letters(), Integer.valueOf(15)));
		System.out.println(MapData.map(new RandomGenertor.Character(), new RandomGenertor.Integer(), 5));
		System.out.println(MapData.map(new RandomGenertor.Integer(), 5, 5));
	}
}
class Letters implements Generator<Pair<Integer,String>>,Iterable<Integer>{
	private int size = 9;
	private int number = 1;
	private char letter = 'A';
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Integer next() {
				return number++;
			}
			
			@Override
			public boolean hasNext() {
				return number < size;
			}
		};
	}

	@Override
	public Pair<Integer, String> next() {
		return new Pair<Integer, String>(number++, ""+letter++);
	}
	
}
