package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;

public class BufferedInputFile {
	private static LinkedList<String> list =new LinkedList<>();
	
	public static String read(String filename) throws Exception {
		BufferedReader buf = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sbu = new StringBuilder();
		while((s=buf.readLine())!=null) {
			sbu.append(s+"\n");
		}
		buf.close();
		return sbu.toString();
	}
	public static void readToList(String filename) throws Exception {
		BufferedReader buf = new BufferedReader(new FileReader(filename));
		String s;
		while((s=buf.readLine())!=null) {
			list.add(s.toUpperCase());
		}
		buf.close();
		Collections.reverse(list);
		for(String str : list) {
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(read("D:\\hf\\eclipse-workspace\\ThinkInJava\\src\\com\\hf\\lesson18\\BufferedInputFile.java"));
		readToList("D:\\hf\\eclipse-workspace\\ThinkInJava\\src\\com\\hf\\lesson18\\BufferedInputFile.java");
	}
}
