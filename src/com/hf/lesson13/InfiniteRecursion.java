package com.hf.lesson13;

import java.util.ArrayList;
import java.util.List;

/**
 * 无意识的递归
 * @author ciker
 * @desc   
 *
 */
public class InfiniteRecursion {
	public String toString() {
		// this 与 String对象相连接时，this自动调用toString()将自身转为string对象，此时就会出现无限递归调用，一直到StackOverflowError（线程栈溢出）
//		return " InfiniteRecursion address "+this+"\n";
		return  " InfiniteRecursion address "+super.toString()+"\n";
	}
	public static void main(String[] args) {
		List<InfiniteRecursion> list = new ArrayList<>();
		for(int i=0;i<10;i++) {
			list.add(new InfiniteRecursion());
		}
		System.out.println(list);
	}
}
