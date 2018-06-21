package com.hf.lesson18;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// 压缩文件GZIP
public class GZIPcompress {
	public static void main(String[] args) throws Exception{
		File file= new File("D:\\xiaoshuo\\秦吏.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("D:\\xiaoshuo\\秦吏.txt.gz")));
		String c;
		while((c=in.readLine())!=null) {
			c+="\n";
			out.write(c.getBytes("utf-8"));
		}
		in.close();
		out.close();
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("D:\\xiaoshuo\\秦吏.txt.gz")),"utf-8"));
		String line;
		while((line=br.readLine())!=null) {
			System.out.println(line);
		}
		br.close();
		
		
	}
}
