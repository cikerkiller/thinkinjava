package com.hf.lesson18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
// 练习二
public class SortedDirList {
	
	private String path;
	public SortedDirList(String path) {
		this.path=path;
	}
	
	public String[] list() throws FileNotFoundException {
		File file = new File(path);
		if(!file.exists()) {
			throw new FileNotFoundException(path);
		}
		String[] fileList=file.list();
		Arrays.sort(fileList,String.CASE_INSENSITIVE_ORDER);
		return fileList;
	}
	
	public String[] list(final String regex) throws FileNotFoundException {
		File file = new File(path);
		if(!file.exists()) {
			throw new FileNotFoundException(path);
		}
		return file.list(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		});
	}
}
