package com.hf.lesson18;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class Person {
	private String first, last;
	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	public Element getXml() {
		Element person = new Element("person");
		Element firstName = new Element("first");
		firstName.appendChild(first);
		Element lastName = new Element("last");
		lastName.appendChild(last);
		person.appendChild(firstName);
		person.appendChild(lastName);
		return person;
	}
	
	public Person(Element person) {
		first = person.getFirstChildElement("first").getValue();
		last = person.getFirstChildElement("last").getValue();
	}
	
	public String toString() {
		return first+ " "+last;
	}
	
	public static void format(OutputStream os, Document doc) throws Exception{
		Serializer serializer = new Serializer(os, "utf-8");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}
	
	public static void main(String[] args) throws Exception{
		List<Person> persons = Arrays.asList(new Person("hf", "ei"),new Person("god", "mo"));
		System.out.println(persons);
		Element root = new Element("people");
		for(Person p:persons) {
			root.appendChild(p.getXml());
		}
		
		Document doc = new Document(root);
		format(System.out, doc);
		
		
		format(new BufferedOutputStream(new FileOutputStream("C:\\Users\\WIN10\\Desktop\\person1.xml")), doc);
		
		
	}
}
