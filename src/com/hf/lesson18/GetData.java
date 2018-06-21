package com.hf.lesson18;

import java.nio.ByteBuffer;
// 获取基本类型
public class GetData {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(BSIZE);
		int i=0;
		while(i++<buf.limit()) {// 大小为1024
			if(buf.get()!=0) {// buf中初始化全部空间中的值都为0
				System.out.println("nonzero");
			}
		}
		System.out.println("i = "+i);
		buf.rewind();// 此处不重置的话，位置已经到末尾了，所有下面在向buf中添加数据会报缓冲器超出异常
		
		buf.asCharBuffer().put("this is");
		char c;
		while((c=buf.getChar())!=0) {
			System.out.print(c);//16位，存储Unicode码，用单引号赋值。
		}
		System.out.println();
		buf.rewind();
		
		buf.asShortBuffer().put((short)32765);//数据范围是-32768~32767之间。-2^15~2^15-1
		System.out.println(buf.getShort());// short 16位，2^16-1==65536-1=65535
		buf.rewind();
		
		buf.asIntBuffer().put(2147483647);
		System.out.println(buf.getInt());// int 32位，最大范围是-2^31~2^31-1
		buf.rewind();
		
		buf.asLongBuffer().put(99471142);
		System.out.println(buf.getLong());
		buf.rewind();
		
		buf.asFloatBuffer().put(99.47f);
		System.out.println(buf.getFloat());
		buf.rewind();
		
		buf.asDoubleBuffer().put(99471142);
		System.out.println(buf.getDouble());
		buf.rewind();
		
		
	}
}
