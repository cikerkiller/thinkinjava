package com.hf.lesson18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPCompress {
	public static void main(String[] args) throws Exception{
		args = new String[] {"D:\\hf\\eclipse-workspace\\ThinkInJava\\src\\com\\hf\\lesson18\\ZIPCompress.java"};
		FileOutputStream f = new FileOutputStream("D:\\\\xiaoshuo\\\\秦吏.txt.zip");
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zo = new ZipOutputStream(csum);
		BufferedOutputStream out = new BufferedOutputStream(zo);
		zo.setComment("this is a test");
		for(String arg :args) {
			System.out.println("writing file: "+arg);
			   
			BufferedReader br = new BufferedReader(new FileReader(arg));
			zo.putNextEntry(new ZipEntry(arg));
			int c;
			while((c=br.read())!=-1) {
				out.write(c);
			}
			br.close();
			out.flush();
		}
		out.close();
		System.out.println("Checksum: "+csum.getChecksum().getValue());
		
		System.out.println("Reading file");
		
		FileInputStream fi = new FileInputStream("D:\\\\xiaoshuo\\\\秦吏.txt.zip");
		CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
		ZipInputStream zi = new ZipInputStream(csumi);
		BufferedInputStream bi = new BufferedInputStream(zi);
		ZipEntry ze;
		while((ze=zi.getNextEntry())!=null) {
			System.out.println("Reading file: "+ze);
			int x;
			while((x=bi.read())!=-1) {
				System.out.write(x);
			}
		}
		
		System.out.println("checksum: "+csumi.getChecksum().getValue());
		bi.close();
		
		
		
	}
}
