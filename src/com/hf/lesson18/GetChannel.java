package com.hf.lesson18;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
// 旧io中三个被改写的类 FileOutputStream FileInputStream RandomAccessFile
public class GetChannel {
	private static final int BSIZE = 1024;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FileChannel fc = new FileOutputStream("C:\\Users\\WIN10\\Desktop\\2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text sds".getBytes()));
		fc.close();
		
		fc = new RandomAccessFile(new File("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt"), "rw").getChannel();
//		fc.position(fc.size());// position 位置 此处移动到末尾
		fc.position(5);// 
		fc.write(ByteBuffer.wrap("some more asfsgd".getBytes()));
		fc.close();
	
		fc = new FileInputStream("C:\\Users\\WIN10\\Desktop\\2.txt").getChannel();
		ByteBuffer buf = ByteBuffer.allocateDirect(BSIZE);
		fc.read(buf);
		buf.flip();// 此处是让缓冲器做好让别人读取字节的准备，适用于获取最大速度
		while(buf.hasRemaining()) {// 判断是否还有剩余
			System.out.print((char)buf.get());// char 无符号 0-2^16-1 byte 有符号 -128~127 byte强转为char类型的话 当byte为负的时候byte值要加上256
		}
	}
}
