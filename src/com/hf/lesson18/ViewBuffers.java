package com.hf.lesson18;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
// 视图缓冲器
public class ViewBuffers {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.wrap(new byte[] {0,0,0,0,0,0,0,'a'});// byte 为8位*8=64bit char 16位*4=64bit int 32*2 float 32*2 double 64*1 long 64*1  short 16*4
//		buf.rewind();
		while(buf.hasRemaining()) {
			System.out.print(buf.position()+"-->"+buf.get()+"\n");
		}
		System.out.println("===========char");
		CharBuffer cb = ((ByteBuffer)buf.rewind()).asCharBuffer();
		while(cb.hasRemaining()) {
			System.out.print(cb.position()+"-->"+cb.get()+"\n");
		}
		
		System.out.println("===========float");
		FloatBuffer fb = ((ByteBuffer)buf.rewind()).asFloatBuffer();
		while(fb.hasRemaining()) {
			System.out.print(fb.position()+"-->"+fb.get()+"\n");
		}
		
		System.out.println("===========int");
		IntBuffer ib = ((ByteBuffer)buf.rewind()).asIntBuffer();
		while(ib.hasRemaining()) {
			System.out.print(ib.position()+"-->"+ib.get()+"\n");
		}
		
		System.out.println("===========long");
		LongBuffer lb = ((ByteBuffer)buf.rewind()).asLongBuffer();
		while(lb.hasRemaining()) {
			System.out.print(lb.position()+"-->"+lb.get()+"\n");
		}

		System.out.println("===========short");
		ShortBuffer sb = ((ByteBuffer)buf.rewind()).asShortBuffer();
		while(sb.hasRemaining()) {
			System.out.print(sb.position()+"-->"+sb.get()+"\n");
		}
		
		System.out.println("===========double");
		DoubleBuffer db = ((ByteBuffer)buf.rewind()).asDoubleBuffer();
		while(db.hasRemaining()) {
			System.out.print(db.position()+"-->"+db.get()+"\n");
		}
		
	}
}
