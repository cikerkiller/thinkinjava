package com.hf.lesson18;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class FormattedMemoryInput {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		DataInputStream in = new DataInputStream(new FileInputStream(new File("D:\\\\hf\\\\eclipse-workspace\\\\ThinkInJava\\\\src\\\\com\\\\hf\\\\lesson18\\\\BufferedInputFile.java")));
//		DataInputStream in = new DataInputStream(
//				new ByteArrayInputStream(
//						BufferedInputFile.read("D:\\\\hf\\\\eclipse-workspace\\\\ThinkInJava\\\\src\\\\com\\\\hf\\\\lesson18\\\\BufferedInputFile.java").getBytes()
//						));
	
		while(in.available()!=0) {// 使用available来查看还有多少可以存取的字符(在么有阻塞的情况下所能读取的字节数)
			System.out.print((char)in.readByte());
		}
//		while(true) {
//			System.out.print((char)in.readByte());
//		}
	}
}
