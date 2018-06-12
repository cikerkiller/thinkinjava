package com.hf.lesson16;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfGenerics {
	public static void main(String[] args) {
//		List<String>[] ls = new ArrayList<String>[5];// 不能创建实际的持有泛型的数组对象
		List<String>[] ls ;
		List[] la = new List[10];
		ls = (List<String>[])la;// 可以创建非泛型数组然后在转型
		ls[0]=new ArrayList<String>();
//		ls[0]=new ArrayList<Integer>();//error  
		ls[0].add("this");
		String str = ls[0].get(0);
		System.out.println(str);
	}
}
