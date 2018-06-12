package com.hf.lesson11;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 统计
 * @author ciker
 * @desc   
 *
 */
public class Statistics {
	public static void main(String[] args) {
		Random random=new  Random(47);
		Map<Integer, Integer> m = new TreeMap<>();
		for(int i=0;i<100000;i++) {
			int r = random.nextInt(20);// between 0 and 20
			Integer freq = m.get(r);
			m.put(r, freq==null?1:freq+1);
		}
		System.out.println(m);
	}
}
