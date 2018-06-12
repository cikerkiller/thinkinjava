package com.hf.lesson15;

import java.util.ArrayList;
import java.util.Random;
/**
 * 随机选取元素
 * @author ciker
 * @desc   
 *
 */
public class RandomList<T> {
	private ArrayList<T> storage = new ArrayList<>();
	private Random rand = new Random(47);// 47代表种子，随机数算法所需，不传47则取当前日期的毫秒数来作为种子
	public void add(T item) {storage.add(item);}
	public T select() {
		return storage.get(rand.nextInt(storage.size()));
	}
	public static void main(String[] args) {
		RandomList<String> rs = new RandomList<>();
		for(String s: "this is a test".split(" ")) {
			rs.add(s);
		}
		int i=0;
		while(true) {
			System.out.println(rs.select());
			if(i++==50) {
				break;
			}
		}
	}
}
