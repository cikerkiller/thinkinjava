package com.hf.lesson16;

import java.util.Arrays;

/**
 * 数组比较
 * @author ciker
 * @desc   
 *
 */
public class ComparingArrays {
	public static void main(String[] args) {
		int[] a1 =new int[10];
		int[] a2 = new int[10];
		Arrays.fill(a1, 47);
		Arrays.fill(a2, 47);
		System.out.println(Arrays.equals(a1, a2));
		a1[2]=14;
		System.out.println(Arrays.equals(a1, a2));
		
		String[] s1 = new String[3];
		Arrays.fill(s1, "hi");
		String[] s2 = {"hi","hi","hi"};
		System.out.println(Arrays.equals(s1, s2));// 数组相当基于内容
		Test[] tt=new Test[5];
		Test[] tt2=new Test[5];
		Arrays.fill(tt2, new Test(5));
		Arrays.fill(tt, new Test(5));
		System.out.println(Arrays.equals(tt2,tt));
		new Test(1).f();
		
	}
	
	static class Test{
		private int i;
		public Test(int i) {
			this.i=i;
		}
		public void f() {
			System.out.println(this.getClass().getSimpleName());
			
		}
		
	}
	
}
