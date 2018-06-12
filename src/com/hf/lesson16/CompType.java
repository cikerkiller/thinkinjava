package com.hf.lesson16;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.hf.lesson15.Generator;

public class CompType implements Comparable<CompType> {
	int i;
	int j;
	private static int count =1;
	
	public CompType(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	
	public String toString() {
		String result = "[i= "+i+", j="+j+"]";
		if(count++%3==0) {
			result +="\n";
		}
		return result;
	}

	@Override
	public int compareTo(CompType o) {
		return (i<o.i?-1:(i==o.i?0:1));
	}
	
	private static Random rand = new Random(47);
	public static Generator<CompType> generator(){
		return new Generator<CompType>() {
			@Override
			public CompType next() {
				return new CompType(rand.nextInt(100), rand.nextInt(100));
			}
		};
	}
	
	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[5], generator());
		System.out.println(Arrays.toString(a));
		Arrays.sort(a,Collections.reverseOrder());// 反向排序
		System.out.println(Arrays.toString(a));
	}
}
