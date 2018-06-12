package com.hf.lesson15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fill {
	public static <T> void fill(Collection<T> col,Class<? extends T> type,int size){
		try {
			for(int i=0;i<size;i++) {
				col.add(type.newInstance());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public static void main(String[] args) {
		List<Contract> list = new ArrayList<>();
		fill(list,Contract.class,5);
		for(Contract c :list) {
			System.out.println(c);
		}
		
		SimpleQueue<Contract> sc = new SimpleQueue<>();
//		fill(sc,Contract.class,8);// 虽然simplequeue有add方法，但是其不是集合子类，所有不能编译通过
	}
}
class Contract{
	private static long counter=0;
	private final long id = counter++;
	public String toString() {
		return getClass().getSimpleName()+" "+id;
	}
}
