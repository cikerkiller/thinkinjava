package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hf.lesson15.Generator;
import com.hf.lesson16.RandomGenertor;

public class FillingLists {
	public static void main(String[] args) {
		List<StringAddress> list = new ArrayList<>(
				Collections.nCopies(4, new StringAddress("Hello")));
		System.out.println(list);
		
		Collections.fill(list, new StringAddress("world!"));
		System.out.println(list);
		
		List<String> slist=new ArrayList<>(CollectionData.list(new RandomGenertor.String(), 5));
		System.out.println(slist);
		
	}
}
class StringAddress{
	private static Generator<Integer> gen=new RandomGenertor.Integer(100);
	private String s;
	public StringAddress(String s) {
		this.s=s;
	}
	public String toString() {
		String string = super.toString();
		return string.substring(0, string.indexOf("@"))+"@"+Integer.parseInt(string.substring(string.indexOf("@")+1),16)+" "+s;
	}
	@Override
	public int hashCode() {
		Integer next = gen.next();
		return next;
	}
	
}