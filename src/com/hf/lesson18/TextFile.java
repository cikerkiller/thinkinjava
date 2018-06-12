package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
	
	private static Map<Character, Integer> countMap = new HashMap<>();
	// 练习17
	public static void readChar(String filename) {
		try(DataInputStream in = new DataInputStream(new FileInputStream(new File(filename).getAbsoluteFile()))){
			while(in.available()!=0) {
				char c = (char)in.readByte();
				if(new Character(c).equals(' ')) {
					continue;
				}
				Integer i = countMap.get(c);
				if(i!=null) {
					countMap.put(c, ++i);
				}else {
					countMap.put(c, 1);
				}
			}
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// 读取文件
	public static String read(String filename) {
		StringBuilder sbu = new StringBuilder();
		try(BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()));){
			String s;
			while((s=in.readLine())!=null) {
				sbu.append(s);
				sbu.append("\n");
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sbu.toString().toString();
		
	}
	
	public static void write(String filename, String text) {
		try(PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile())){
			out.print(text);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TextFile(String filename, String splitter) {
		super(Arrays.asList(read(filename).split(splitter)));
		if(get(0).equals("")) {
			remove(0);
		}
	}
	
	public TextFile(String filename) {
		this(filename,"\n");
	}
	
	public void write(String filename) {
		try(PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile())){
			for(String item : this) {
				out.println(item);
			}
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		
		String file = read("C:\\Users\\WIN10\\Desktop\\2.txt");
		write("test.txt", file);
		
		TextFile text = new TextFile("test.txt");
		text.write("test2.txt");
		
		TreeSet<String> words = new TreeSet<>(new TextFile("C:\\Users\\WIN10\\Desktop\\2.txt","\\W+"));
		System.out.println(words.headSet("test"));// 在set中首次出现指定元素之前的所有元素集合
		
		
		readChar("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt");
		System.out.println(countMap);
		
	}
	
}
