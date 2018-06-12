package com.hf.lesson15;

import java.util.HashSet;
import java.util.Set;

public class Sets {
	// 并集
	public static <T> Set<T> union(Set<T> a,Set<T> b){
		Set<T> result =new HashSet<>(a);
		result.addAll(b);
		return result;
	}
	
	// 交集
	public static <T> Set<T> intersection(Set<T> a , Set<T> b){
		Set<T> result =new HashSet<>(a);
		result.retainAll(b);
		return result;
	}
	
	// 从superset移除subset包含的元素
	public static <T> Set<T> difference(Set<T> superset , Set<T> subset){
		Set<T> result =new HashSet<>(superset);
		result.removeAll(subset);
		return result;
	}
	
	// 返回交集以外的元素
	public static <T> Set<T> complement(Set<T> a , Set<T> b){
		return difference(union(a, b), intersection(a, b));
	}
	
}
