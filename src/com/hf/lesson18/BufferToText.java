package com.hf.lesson18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws Exception{
		FileChannel fc = new FileOutputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getChannel();
		fc.write(ByteBuffer.wrap("this is a test".getBytes()));
		fc.close();
		
		fc = new FileInputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getChannel();
		ByteBuffer buf = ByteBuffer.allocate(BSIZE);
		fc.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());// 直接不行，字符编码问题，会出现乱码
		buf.rewind();// 倒带
		String encoding = System.getProperty("file.encoding");
		System.out.println("encoding: "+encoding+"  ,  "+Charset.forName(encoding).decode(buf));
		
		fc = new FileOutputStream("C:\\\\\\\\Users\\\\\\\\WIN10\\\\\\\\Desktop\\\\\\\\2.txt").getChannel();
		fc.write(ByteBuffer.wrap("some txt".getBytes("UTF-16BE")));
		fc.close();
		
		fc = new FileInputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getChannel();
		buf.clear();
		fc.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());// 
		
		fc = new FileOutputStream("C:\\\\\\\\Users\\\\\\\\WIN10\\\\\\\\Desktop\\\\\\\\2.txt").getChannel();
		buf = ByteBuffer.allocate(25);// 25个字节，12个字符左右
		buf.asCharBuffer().put("some fh");//向缓冲器放入大于设置的存储空间大小会抛异常 
		fc.write(buf);
		fc.close();
		
		fc = new FileInputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getChannel();
		buf.clear();
		fc.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());
		
	}
}
