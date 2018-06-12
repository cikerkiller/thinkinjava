package com.hf.lesson15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class New {
	public static <K,V> Map<K,V> map(){
		return new HashMap<K, V>();
	}
	
	public static <T> List<T> list(){
		return new ArrayList<>();
	}
	public static <T> LinkedList<T> lList(){
		return new LinkedList<>();
	}
	public static <T> Set<T> set(){
		return new HashSet<>();
	}
	public static <T> Queue<T> queue(){
		return new LinkedList<>();
	}
	
}
