package com.hf.lesson18;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TransferTo {
	public static void main(String[] args) throws Exception  {
		FileChannel in = new FileInputStream(new File("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getAbsoluteFile()).getChannel(),
				out = new FileOutputStream(new File("C:\\\\Users\\\\WIN10\\\\Desktop\\\\5.txt").getAbsoluteFile()).getChannel();
//		in.transferTo(0, in.size(), out);// 从输入流传输到输出流
		out.transferFrom(in, 0, in.size());
	}
}
