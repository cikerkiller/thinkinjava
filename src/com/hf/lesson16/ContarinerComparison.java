package com.hf.lesson16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContarinerComparison {
	public static void main(String[] args) {
		BerlliumSphere[] spheres =new BerlliumSphere[10];
		for(int i=0;i<5;i++) {
			spheres[i] = new BerlliumSphere();
		}
		System.out.println(Arrays.toString(spheres));
		System.out.println(spheres[4]);
		
		List<BerlliumSphere> list = new ArrayList<>();
		for(int i=0;i<5;i++) {
			list.add(new BerlliumSphere());
		}
		System.out.println(list);
		
		int[] integer = {1,2,3,4,5};
		System.out.println(Arrays.toString(integer));
		
		List<Integer> intList = new ArrayList<>(Arrays.asList(0,1,2,3,4,5));
		intList.add(97);
		System.out.println(intList);
		char[] ca=new char[5];
		for(int i=0;i<5;i++) {
			System.out.println(((char)0)+",");
		}
		
	}
}
class BerlliumSphere{
	private static long counter=0;
	private final long id = counter++;
	private long idd;
	public String toString() {
		return "Sphere "+id;
	}
	public void set(long id) {
		this.idd=id;
	}
	public long idd() {
		return idd;
	}
	
}