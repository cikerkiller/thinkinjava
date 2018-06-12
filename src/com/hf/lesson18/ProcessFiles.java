package com.hf.lesson18;

import java.io.File;
import java.io.IOException;
// 处理文件，根据生成的策略及给出的匹配文件扩展名
public class ProcessFiles {
	public interface Strategy {
		void process(File file);
	}
	
	private Strategy strategy;// 策略
	private String ext;// 扩展名
	public ProcessFiles(Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}
	public void start(String...args) {
		try {
			if(args.length == 0) {
				processDirectoryTree(new File("."));
			}else {
				for(String arg : args) {
					File fileArg = new File(arg);
					if(fileArg.isDirectory()) {
						processDirectoryTree(fileArg);
					}else {
						if(!arg.endsWith("."+ext)) {
							arg += "."+ext;
						}
						strategy.process(new File(arg).getCanonicalFile());
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void processDirectoryTree(File root) throws IOException {
		// 遍历目录下所有匹配的文件
		for(File file : Directory.walk(root.getAbsolutePath(),".*\\."+ext)) {  
			strategy.process(file);
		}
	}
	
	public static void main(String[] args) {
		new ProcessFiles(new Strategy() {
			
			@Override
			public void process(File file) {
				System.out.println(file);
			}
		},"java").start(args);;
	}
	
	
}
