package com.hf.lesson13;

import java.util.Arrays;

public class Splitting {
	public static String knights = "this is a Test,haha!";
	
	public static void split(String regex) {
		System.out.println(Arrays.toString(knights.split(regex)));
	}
	public static void main(String[] args) {
		split(" ");// 空格分开
		split("\\W+");// 以非单词字符分割，出现的就是单词数组[this, is, a, Test, haha]
		split("\\w+");// 以单词字符分割，出现的是非单词数组（即标点符号，空格之类的）[,  ,  ,  , ,, !]
		split("s\\W+");// 以字母s后面跟着一个或多个非单词字符分割[thi, i, a Test,haha!]
		split("i\\w+");// 以字母i后面跟着一个或多个单词字符分割[th,  ,  a Test,haha!]
		
		
		System.out.println(knights.replaceFirst("h\\w", "a"));
		System.out.println(knights.replaceAll("h\\w+", "a"));
		System.out.println(knights.replace("is", "a"));
		System.out.println(knights.replace("a", "_"));
		split("(i|a)\\w+");//已i或a开头后面跟着一个或多个单词字符为分割线分割
		split("[ia]\\w+");//已i或a开头后面跟着一个或多个单词字符为分割线分割
		split("^t");
		split("\\B");
		split("\\b");  
	}
}
