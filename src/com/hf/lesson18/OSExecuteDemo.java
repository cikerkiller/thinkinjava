package com.hf.lesson18;

public class OSExecuteDemo {
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		OSExecute.command("CMD /D javap D:\\hf\\eclipse-workspace\\ThinkInJava\\bin\\com\\hf\\lesson18\\OSExecuteDemo");
	}
}
