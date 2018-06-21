package com.hf.lesson18;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;
// 文件加锁
public class FileLocking {
	public static void main(String[] args) throws Exception
	{
		FileOutputStream fo = new FileOutputStream("file.txt");
		FileLock lock = fo.getChannel().tryLock();
		if(lock!=null) {
			
			System.out.println(lock.isValid());
			System.out.println("Locked file");
			TimeUnit.MICROSECONDS.sleep(1000);
			lock.release();
			System.out.println("Release lock");
		}
		fo.close();
	}
}
