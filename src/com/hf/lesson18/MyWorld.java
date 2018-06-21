package com.hf.lesson18;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyWorld {
	public static void main(String[] args) throws Exception{
		House house = new House();
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal("Bosco", house));
		animals.add(new Animal("Ralph", house));
		animals.add(new Animal("Molly", house));
		System.out.println(animals);
		
		ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
		ObjectOutputStream o1 = new ObjectOutputStream(buf1);
		o1.writeObject(animals);
		o1.writeObject(animals);// 深克隆

		ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
		ObjectOutputStream o2 = new ObjectOutputStream(buf2);
		o2.writeObject(animals);
		
		ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
		ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
		List<Animal> animals1 = (List<Animal>)in1.readObject();
		List<Animal> animals2 = (List<Animal>)in1.readObject();
		List<Animal> animals3 = (List<Animal>)in2.readObject();
		System.out.println(animals1);
		System.out.println(animals2);
		System.out.println(animals3);
		
		
		
		
		
		
	}
}
class House implements Serializable{}
class Animal implements Serializable{
	private String name;
	private House preferredHouse;
	Animal(String nm,House h){
		this.name=nm;
		this.preferredHouse=h;
	}
	public String toString() {
		return name+"["+super.toString()+"], "+preferredHouse+"\n";
	}
}