package com.hf.lesson18;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blips {
	public static void main(String[] args) throws Exception {
		System.out.println("Constructing objects");
		Blip1 blip1 = new Blip1();
		Blip2 blip2 = new Blip2();
		
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("C:\\Users\\WIN10\\Desktop\\Blips.out"));
		System.out.println("Saving Objects");
		o.writeObject(blip1);// 保存的时候会调用writeExternal
		o.writeObject(blip2);
		o.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\WIN10\\Desktop\\Blips.out"));
		System.out.println("Recovering b1: ");
		blip1 = (Blip1)in.readObject();// 读取的时候会先调用公共的默认构造器，然后调用readExternal
		System.out.println("Recovering b2: ");
		blip2 = (Blip2)in.readObject();//com.hf.lesson18.Blip2; no valid constructor
		in.close();
	}
}
// 实现Externalizable 的类，在恢复时都要调用默认构造器,而且还要显示的写出默认构造器,且构造器必须为public
class Blip1 implements Externalizable {
	
	public Blip1() {
		System.out.println("Blip1 constructor");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("blip1.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("blip1.readExternal");
	}
	
}

class Blip2 implements Externalizable {
//	public Blip2() {
//		System.out.println("Blip2 constructor");
//	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip2.readExternal");
	}
	
}
