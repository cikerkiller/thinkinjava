package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 未获支持的操作
 * @author ciker
 * @desc   
 *
 */
public class Unsupported {
	public static void test(String msg,List<String> list) {
		System.out.println("============="+msg+"=============");
		Collection<String> c = list;
		List<String> list2 = list.subList(1, 8);
		Collection<String> c2 = new ArrayList<>(list2);
		
		try {c.retainAll(c2);}catch(Exception e) {System.out.println("retainAll "+e);}
		try {c.removeAll(c2);}catch(Exception e) {System.out.println("removeAll "+e);}
		try {c.clear();}catch(Exception e) {System.out.println("clear "+e);}
		try {c.add("X");}catch(Exception e) {System.out.println("add "+e);}
		try {c.addAll(c2);}catch(Exception e) {System.out.println("addAll "+e);}
		try {c.remove("C");}catch(Exception e) {System.out.println("remove "+e);}
		try {list.set(0, "sdf");}catch(Exception e) {System.out.println("set "+e);}
		
	}
	
	public static void main(String[] args) {
		List<String> list =Arrays.asList("A B C D E F G H I J K L M N".split(" "));
		
		test("Arrays.asList", list);// 基于固定大小的数组,仅支持不会改变数组大小的操作
		
		test("ArrayList", new ArrayList<>(list));
		test("unmodifiableList", Collections.unmodifiableList(new ArrayList<>(list)));// 不可修改
		
		
		
	}
	
}
