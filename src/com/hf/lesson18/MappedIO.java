package com.hf.lesson18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

// 内存映射文件与流性能测试框架  
public class MappedIO {
	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;
	private abstract static class Tester {
		private String name;
		public Tester(String name) { this.name = name; }
		public void runTest() {
			System.out.print(name+": ");
			try {
				long start = System.nanoTime();
				test();
				double duration = System.nanoTime() - start;
				System.out.format("%.2f\n",duration/1.0e9);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
		public abstract void test() throws Exception;
	}
	
	private static Tester[] tests = {
			
			new Tester("Stream Write") {
				
				@Override
				public void test() throws Exception {
					DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("temp.tmp")));
					for(int i=0;i<numOfInts;i++) {
						dos.writeInt(i);
					}
					dos.close();
				}
			},
			
			new Tester("Mapped Write") {
				
				@Override
				public void test() throws Exception {
					FileChannel fc = new RandomAccessFile("temp.tmp","rw").getChannel();
					MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
					IntBuffer ib = mb.asIntBuffer();
					for(int i=0;i<numOfInts;i++) {
						ib.put(i);
					}
					fc.close();
				}
			},
			
			
			new Tester("stream read") {
				
				@Override
				public void test() throws Exception {
					DataInputStream dos = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
					for(int i=0;i<numOfInts;i++) {
						dos.readInt();
					}
					dos.close();
				}
			},
			
			new Tester("Mapped read") {
				
				@Override
				public void test() throws Exception {
					FileChannel fc = new FileInputStream("temp.tmp").getChannel();
					MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
					IntBuffer ib = mb.asIntBuffer();
					while(ib.hasRemaining()) {
						ib.get();
					}
					fc.close();
				}
			},
			
			new Tester("stream read/write") {
				
				@Override
				public void test() throws Exception {
					RandomAccessFile raf = new RandomAccessFile("temp..tmp","rw");
					raf.writeInt(1);
					for(int i=0;i<numOfUbuffInts;i++) {
						raf.seek(raf.length()-4);
						raf.writeInt(raf.readInt());
					}
					raf.close();
				}
			},
			
			new Tester("Mapped read/Write") {
				
				@Override
				public void test() throws Exception {
					FileChannel fc = new RandomAccessFile("temp.tmp","rw").getChannel();
					MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
					IntBuffer ib = mb.asIntBuffer();
					ib.put(0);
					for(int i=1;i<numOfUbuffInts;i++) {
						ib.put(ib.get(i-1));
					}
					fc.close();
				}
			},
			
	};
	
	public static void main(String[] args) {
		for(Tester t : tests) {
			t.runTest();
		}
	}
	
}
