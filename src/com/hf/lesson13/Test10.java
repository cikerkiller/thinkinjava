package com.hf.lesson13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test10 {
	private static String s = "Java now has regular expressions";
	public static void main(String[] args) {
		match("^Java");
		match("\\Breg.*");
		match("n.w\\s+h(a|i)s");// n开头.任意字符后面跟w \s空白符+一个或多个  跟着h 后面接a或者i 再跟s
		match("s?");// 一个或零个s
		match("s*");// 多个或零个s
		match("s+");// 多个或一个s
		match("s{4}");// 恰好4个
		match("s{1}.");// 恰好1个后面跟任意字符
		match("s{0,3}");// 至少0个不超过3个
		
		
		
		
		
	}
	private static void match(String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher matcher=p.matcher(s);
		// 包含
		System.out.println("find: "+matcher.find());
		// 全匹配
		System.out.println("matches: "+matcher.matches());
	}
}
