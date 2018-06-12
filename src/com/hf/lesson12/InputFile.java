package com.hf.lesson12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
	private BufferedReader in;
	public InputFile(String filename) throws Exception{
		try {
			in =  new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("could not open "+filename);
			throw e;
		}catch (Exception e) {
			try {
				in.close();
			} catch (Exception e1) {
				System.out.println("in.close() unsuccessful");
			}
			throw e;
		}finally {}
	}
	
	public String getLine() {
		String s;
		try {
			s=in.readLine();
		} catch (IOException e) {
			throw new RuntimeException("readLine() failed");
		}
		return s;
	}
	
	public void dispose() {
		try {
			in.close();
			System.out.println("dispose() successful");
		} catch (IOException e) {
			throw new RuntimeException("in.close() failed");
		}
	}
}
