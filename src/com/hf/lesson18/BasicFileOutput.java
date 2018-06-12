package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;

public class BasicFileOutput {
	public static void main(String[] args) throws Exception {
		// 退出try块才会自动关闭流,关闭流缓冲区才会刷新清空
		try(BufferedReader  in = new BufferedReader(
				new StringReader(
						BufferedInputFile.read("D:\\\\hf\\\\eclipse-workspace\\\\ThinkInJava\\\\src\\\\com\\\\hf\\\\lesson18\\\\BufferedInputFile.java")));
//			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\WIN10\\Desktop\\2.txt")));
			// printWriter 在java se5时添加了一个辅助构造器，不必执行装饰工作
			//  this(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))),false);
			PrintWriter out = new PrintWriter("C:\\Users\\WIN10\\Desktop\\2.txt");
				){
			String s;
			while((s=in.readLine())!=null) {
				out.println(s);
			}
//			out.flush();// 显示刷新清空缓冲区
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(BufferedInputFile.read("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt"));
	}
}
