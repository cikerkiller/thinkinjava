package com.hf.lesson13;

public class IntegerMatch {
	public static void main(String[] args) {
		// 可能有一个负号或者没有符号，后面跟着多位数字
		System.out.println("-123".matches("-?\\d+"));
		System.out.println("123".matches("-?\\d+"));
		// 有一个加号就不匹配
		System.out.println("+123".matches("-?\\d+"));
		// | 表示或，可能前面是-或者+开头 小括号有将表达式分组的效果  \\ 表示转义（因为+在正则表达式中有特殊含义，需要\\转义）
		System.out.println("+123".matches("(-|\\+)\\d+"));
		System.out.println("-123".matches("(-|\\+)\\d+"));
		System.out.println("123".matches("(-|\\+)\\d+"));// false
		System.out.println("123".matches("(-|\\+)?\\d+"));// true ?表示二者皆没有
	}
}
