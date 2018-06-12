package com.hf.lesson18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File(".");
		String[] list;
		if(args.length==0) {
			list = path.list();
		}else {
			list = path.list(new DirFilter(args[0]));// 策略模式的例子,策略的目的就是提供代码行为的灵活性
		}
		Arrays.sort(list);
		for(String dirItem : list) {
			System.out.println(dirItem);
		}
	}
}

class DirFilter implements FilenameFilter{
	private Pattern pattern;
	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
//		System.out.println(dir.getName());// dir表示当前文件对象,即要遍历的file对象
		return pattern.matcher(name).matches();// 当匹配时加入数组列表
	}
	
}