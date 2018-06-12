package com.hf.lesson16;

import java.util.Arrays;

/**
 * 数组复制
 * @author ciker
 * @desc   
 *
 */
public class CopyingArrays {
	public static void main(String[] args) {
		int[] i = {2,8,6,1,4,5,3};
		int[] j= {8,5,4,6,1,3,5,4,2,8,6,1};
		
//		Arrays.fill(i, 47);
//		Arrays.fill(j, 50);
		System.out.println("i="+Arrays.toString(i));
		System.out.println("j="+Arrays.toString(j));
		//System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		// src 原数组  srcPos 从原数组的此下标开始  dest 目标数组  destPos 目标数组的下标开始  length 复制个数
		System.arraycopy(i, 1, j, 2, 3);// 对对象数组的复制为浅复制
		System.out.println("j="+Arrays.toString(j));
		BerlliumSphere[] bers = IceCream.fill(5);
		BerlliumSphere[] bers1 = {new BerlliumSphere(),new BerlliumSphere(),new BerlliumSphere(),new BerlliumSphere()};
		
		System.arraycopy(bers, 0, bers1, 0, 3);
		System.out.println(Arrays.asList(bers1));
		bers[0].set(12);
		long idd = bers1[0].idd();
		System.out.println(idd);// 12  证明只是浅复制（对引用的复制）第一个数组中的对象改变其值，后一个数组的复制过来的对象也改变了
	}
}
