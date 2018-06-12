package com.hf.lesson16;

import java.util.Arrays;
import java.util.Random;

public class IceCream {
	private static Random rand =new Random(47);
	static final String[] FLAVORS = {"Chocolate","Strawberry","Vanilla Fudge","Mint Chip","this","is","a","fsafa"};
	public static String[] flavorSet(int n) {
		if(n>FLAVORS.length) {
			throw new IllegalArgumentException("Set too big");
		}
		String[] result = new String[n];
		boolean[] picked = new boolean[FLAVORS.length];
		for(int i=0;i<n;i++) {
			int t;
			do {
				t=rand.nextInt(FLAVORS.length);
			}while(picked[t]);
			
			result[i]=FLAVORS[t];
			picked[t]=true;
		}
		return result;
	}
	
	// 练习二
	public static BerlliumSphere[] fill(int n) {
		BerlliumSphere[] ber=new BerlliumSphere[n];
		for(int i=0;i<n;i++) {
			ber[i]=new BerlliumSphere();
		}
		return ber;
	}
	
	public static void main(String[] args) {
		for(int i = 0;i<7;i++) {
			System.out.println(Arrays.toString(flavorSet(3)));
		}
		// 第一维必须指定长度
		int[][][] a = new int[1][2][];
		System.out.println(Arrays.deepToString(a));
	}
}
