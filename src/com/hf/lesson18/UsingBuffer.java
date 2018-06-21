package com.hf.lesson18;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffer {
	// 每两个字符调换位置  
	private static void symmetricScramble(CharBuffer buf) {
		while(buf.hasRemaining()) {
			buf.mark();// 在此时position的指向的位置上做标记
			char c1 = buf.get();
			char c2 = buf.get();// 注意因为每次是两个字符调换位置，所以字符数组一定是双数的，不然会报异常
			buf.reset();// position回到刚才做标记的地方
			buf.put(c2).put(c1);// get put 方法会使position移动
		}
	}
	
	public static void main(String[] args) {
		char[] data = "UsingBuffers".toCharArray();
		ByteBuffer buf = ByteBuffer.allocate(data.length*2);
		CharBuffer cb = buf.asCharBuffer();
		cb.put(data);
		System.out.println(cb.rewind());
		symmetricScramble(cb);
		System.out.println(cb.rewind());
		symmetricScramble(cb);
		System.out.println(cb.rewind());
	}
}
