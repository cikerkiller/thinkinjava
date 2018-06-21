package com.hf.lesson18;

import java.io.File;
import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

public class People extends ArrayList<Person> {
	public People(String filename) throws Exception {
		Document doc = new Builder().build(new File(filename));
		Elements elements = doc.getRootElement().getChildElements();
		for(int i=0;i<elements.size();i++) {
			add(new Person(elements.get(i)));
		}
	}
	public static void main(String[] args) throws Exception {
		People p = new People("C:\\Users\\WIN10\\Desktop\\person1.xml");
		System.out.println(p);
	}
}
