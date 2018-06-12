package com.hf.lesson13;

import java.util.Formatter;

public class Receipt {
	private double total = 0;
	private Formatter f = new Formatter(System.out);
	private int width;
	public Receipt(int width) {
		this.width=width;
	}
	public void printTitle() {
		f.format("%-"+width+"s %5s %10s\n", "Item","Number","Price");
		f.format("%-"+width+"s %5s %10s\n", "----","---","-----");
	}
	//%[argument_index$][flags][width][.precision]conversion
	//％[参数索引$] [标志] [宽度][精度]转换
	public void print(String name,int number,double price) {
		// - 代表左对齐，默认是右对齐
		// . 用于string类型后面跟的precision代表最大输出的字符个数 用于浮点数则是保留小数位
		f.format("%-"+width+".15s %5d %10.2f\n", name,number,price);
		total+=number*price;
	}
	  
	public void printTotal() {
		f.format("%-"+width+"s %5s %10.2f\n", "Tax","",total*0.06);
		f.format("%-"+width+"s %5s %10.2s\n", "","","-----");   
		f.format("%-"+width+"s %5s %10.2f\n", "total","",total*1.06);
	}
	public static void main(String[] args) {
		Receipt receipt=new Receipt(16);
		receipt.printTitle();
		receipt.print("aa", 4, 4.25);  
		receipt.print("bbssssssssssssssssaaaaaaaaa", 3, 5.1);
		receipt.print("cc", 1, 14.29);
		receipt.printTotal();
	}
	
	
}
