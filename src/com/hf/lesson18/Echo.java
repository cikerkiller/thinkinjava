package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Echo {
	public static void main(String[] args) {
		try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))){
			String s;
			while((s=bf.readLine())!=null) {
				System.out.println(s);
			}
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
