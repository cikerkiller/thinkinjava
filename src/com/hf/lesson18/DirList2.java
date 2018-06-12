package com.hf.lesson18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList2 {
	// regex是final这样才能使用来自该类范围之外的参数,因为内部类与外部类是同一级别，内部类不会因为定义在方法中就会跟随方法的结束而不销毁，
	// 若是不定义为final，则当外部类方法执行完后这个参数就会被GC，然而内部类需要这个参数的方法不一定执行完毕，这就gg了，
	// 所以这个参数必须要定义成final，java会将这个变量复制一份作为成员变量内置于内部类
	public static FilenameFilter filter(final String regex) {
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		};
	}
	public static void main(String[] args) {
		File path = new File(".");
		String[] list;
		if(args.length==0) {
			list = path.list();
		}else {
			list = path.list(filter(args[0]));// 策略模式的例子,策略的目的就是提供代码行为的灵活性
		}
		Arrays.sort(list);
		for(String dirItem : list) {
			System.out.println(dirItem);
		}
	}
}
