package com.hf.lesson16;

import java.util.Arrays;

import com.hf.lesson15.Generator;

// 在已排序的数组中查找
public class ArraySearching {
	public static void main(String[] args) {
		Generator<Integer> gen=new RandomGenertor.Integer();
		int[] a=ConvertTo.primitive(Generated.array(new Integer[10], gen));
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		int i=0;
		while(true) {
			int r=gen.next();
			r=10000;
			// search 为(-插入点-1) 若r大于数组中的所有值则插入点为a.length
			int search = Arrays.binarySearch(a, r);
			System.out.println(search);
			if(search>=0) {
				System.out.println("r = "+r+" is a index "+search +"a[search]="+a[search]+"");
				System.out.println(i);
				break;
			}
			i++;
		}
		
	}
}
