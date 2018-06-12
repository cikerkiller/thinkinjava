package com.hf.lesson15.erased;

import java.util.ArrayList;

/**
 * 擦除
 * 
 * @author ciker
 * @desc
 *
 */
public class ErasedTypeEquivalence {
	public static void main(String[] args) {
		Class c1 = new ArrayList<Integer>().getClass();
		Class c2 = new ArrayList<String>().getClass();
		System.out.println(c1 == c2);// true
	}
}
