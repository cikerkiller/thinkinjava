package com.hf.lesson18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
	private static final int SIZE = 1024;
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		FileChannel in = new FileInputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getChannel(),
				out = new FileOutputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\3.txt").getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(SIZE);// 通过告知分配多少存储空间来创建一个bytebuffer 
		while(in.read(buf)!=-1) {// 将数据输入到缓冲器中
			buf.flip();// 准备写
			out.write(buf);// 将缓冲器中的数据写入到文件，但是缓冲器的数据还在
			buf.clear();// 对所有的内部指针重新安排，以便缓冲器在另一次读取期间能做好接收准备
		}
		 
	}
}
