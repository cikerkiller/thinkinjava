package com.hf.lesson18;

import java.io.File;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
	static String file = "rtest.bat";
	static {
//		File files = new File(file);
//		if(!files.exists()) {
//			try {
//				files.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	static void dispaly() throws Exception {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for(int i=0;i<7;i++) {
			System.out.println("Value "+i+": "+rf.readDouble());
		}
		System.out.println(rf.readUTF());
		rf.close();
	}
	public static void main(String[] args) throws Exception {
		boolean path = new File(file).exists();
		System.out.println(path);
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for(int i=0;i<7;i++) {
			rf.writeDouble(i*1.414);
		}
		path = new File(file).exists();
		System.out.println(path);
		rf.writeUTF("The end of the file");
		rf.close();
		dispaly();
		
		rf = new RandomAccessFile(file, "rw");  
		rf.seek(5*8);
		rf.writeDouble(47.0001);
		rf.close();
		dispaly();
		new File(file).deleteOnExit();
		
	}
}
