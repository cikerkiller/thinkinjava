package com.hf.lesson18;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 进程控制
public class OSExecute {
	public static void command(String command) {

		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
			String s;
			while ((s = results.readLine()) != null)
				System.out.println(s);
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(), "gbk"));
			// Report errors and return nonzero value
			// to calling process if there are problems:
			while ((s = errors.readLine()) != null) {
				System.err.println(s);
				err = true;
			}
		} catch (Exception e) {
			// Compensate for Windows 2000, which throws an
			// exception for the default command line:
			if (!command.startsWith("CMD /C"))
				command("CMD /C " + command);
			else
				throw new RuntimeException(e);
		}
		if (err)
			throw new RuntimeException("Errors executing " + command);
	}

	public static void main(String[] args) {
		String str = new String("sss");
		String str1 = "sss";
		String str2 = "sss";
		System.out.println(str1 == str2);

		String str3 = "hello";
		String str4 = "he" + new String("llo");
		System.out.println(str3 == str4);
		
		
	}
}
