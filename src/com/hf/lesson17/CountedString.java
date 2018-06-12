package com.hf.lesson17;
/**
 * 生成hashcode
 * 1、给int变量result赋予某个非零常量如:17
 * 2、给对象的每个可以做equals()的域f计算出一个int散列码c
 * 3、合并计算得到的散列码 result = 37 * result + c
 * 4、返回result
 * 5、检查hashcode()最后生成的结果确保相同的对象生成相同的散列码
 * @author ciker
 * @desc   
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountedString {
	private static List<String> created = new ArrayList<>();
	private String s;
	private int id= 0;
	public CountedString(String str) {
		s = str;
		created.add(s);
		for(String s2 : created) {
			if(s2.equals(s)) {
				id++;
			}
		}
	}
	
	public String toString() {
		return "String "+s+" id: "+id+" hashcode(): "+hashCode();
	}
	
	public int hashCode() {
		int result = 17;
		result = 37 * result +s.hashCode();
		result = 37 * result +id;
		return result;
	}
	
	public boolean equals(Object o ) {
		return o instanceof CountedString 
				&& s.equals(((CountedString)o).s) 
				&& id == ((CountedString)o).id;
	}
	
	public static void main(String[] args) {
		Map<CountedString, Integer> map = new HashMap<>();
		CountedString[] cs = new CountedString[5];
		for(int i=0;i<cs.length;i++) {
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);
		}
		System.out.println(map);
		for(CountedString c : cs) {
			System.out.println(c);
			System.out.println(map.get(c));
		}
	}
}
