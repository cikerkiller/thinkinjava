package com.hf.lesson18;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharSets {
	public static void main(String[] args) {
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		
		Set<String> set = charsets.keySet();
		for(String charset : set ) {
			System.out.print(charset+": ");
			Set<String> aliases = charsets.get(charset).aliases();
			for(String key : aliases) {
				System.out.print(key+", ");
			}
			System.out.println();
		}
	}
}
