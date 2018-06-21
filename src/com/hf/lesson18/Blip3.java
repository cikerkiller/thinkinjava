package com.hf.lesson18;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blip3 implements Externalizable{
	private int i;
	private String s;
	public Blip3() {
		System.out.println("Blipp3 constructor");
	}
	
	public Blip3(String x,int a) {
		System.out.println("Blip3(String x,int a)");
		this.s=x;
		this.i=a;
	}
	
	public String toString() {
		return s+i;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("writeExternal");
		// 此处将字段序列化保存
		out.writeObject(s);
		out.writeInt(i);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("readExternal");
		// 必须在此处初始化，不然本类的字段会使用默认初始化，null以及0
		s=(String)in.readObject();
		i = in.readInt();
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("Constructing objects");
		Blip3 blip3 = new Blip3("A string", 47);
		System.out.println(blip3);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\WIN10\\Desktop\\Blips.out"));
		System.out.println("Saving b3 ");
		out.writeObject(blip3);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\WIN10\\Desktop\\Blips.out"));
		blip3=(Blip3)in.readObject();
		 System.out.println(blip3);
		 in.close();
	}
}
