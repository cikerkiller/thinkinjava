package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// 进程控制
public class OSExecute {
	public static void command(String...command) {
		boolean err = false;
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			processBuilder.directory(new File("D://"));
			Process process = processBuilder.start();
			BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream(),"utf-8"));
			String s;
			while((s=result.readLine())!=null) {
				System.out.println(s);
			}
			
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while((s=errors.readLine())!=null) {
				System.out.println(s);
				err = true;
			}
		} catch (IOException e) {
			throw new OSExecuteException(e);
		}
		if(err) {
			throw new OSExecuteException("errors executing "+Arrays.asList(command));
		}
	}
	
	
}
