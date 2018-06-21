package com.hf.lesson18;

import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {
	static int length = 0x8ffffff;// 128MB
	public static void main(String[] args) throws Exception{
		MappedByteBuffer out = new RandomAccessFile("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		CharBuffer cb = out.asCharBuffer();
		for(int i=0;i<length/2;i++) {
			cb.put('x');
		}
		System.out.println("finished writing");
		for(int i=length/2-6;i<length/2;i++) {
			System.out.print(cb.get(i));
		}
		
		
	}
}
