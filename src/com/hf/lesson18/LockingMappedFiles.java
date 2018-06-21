package com.hf.lesson18;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// 对文件部分加锁，当访问另一个线程访问加锁的文件部分时会出现异常
// 只能获取到文件通道的锁
public class LockingMappedFiles {
	static final int LENGTH = 0x8FFFFFF;
	static FileChannel fc;
	public static void main(String[] args) throws Exception{
		fc = new RandomAccessFile("test.bar", "rw").getChannel();
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		
		for(int i=0;i<LENGTH;i++) {
			out.put((byte)'x');
		}
		new LockAndModify(out, 0, 0+LENGTH/2+3);
		new LockAndModify(out, LENGTH/2, LENGTH/2+LENGTH/4);
	}
	
	
	private static class LockAndModify extends Thread {
		private ByteBuffer buf;
		private int start, end;
		
		public LockAndModify(ByteBuffer buf, int start, int end) {
			this.start=start;
			this.end = end;
			buf.limit(end);
			buf.position(start);
			this.buf = buf.slice();// 生成一个包含主buf一部分的buf
			start();
		}
		
		public void run() {
			try {
				FileLock fl=fc.lock(start, end, false);
				System.out.println("Locked: "+start+" to "+ end);
				
				while(buf.position()<buf.limit()-1) {
					buf.put((byte)(buf.get()+1));
				}
				fl.release();
				System.out.println("released:  "+start +" to "+end);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
