package com.hf.lesson18;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *  读取二进制文件
 * @author ciker
 * @desc   
 *
 */
public class BinaryFile {
	public static byte[] read(File file) {
		try(BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file))){
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] read(String filename) {
		return read(new File(filename).getAbsoluteFile());
	}
}
