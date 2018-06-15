package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// 进程控制
public class OSExecute {
	public static void command(String command) {
		boolean err = false;
		try {
			ProcessBuilder builder = new ProcessBuilder(command.split(" "));
			Process process = builder.start();
			BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));
			String s;
			while((s=result.readLine())!=null) {
				System.out.println(s);
			}
			
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(),"gbk"));
			while((s=errors.readLine())!=null) {
				System.out.println(s);
				err = true;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(err) {
			throw new RuntimeException("errors executing "+Arrays.asList(command));
		}
	}
	public static void main(String[] args) {
		OSExecute.command("CD /D javap OSExecute");
	}
}
