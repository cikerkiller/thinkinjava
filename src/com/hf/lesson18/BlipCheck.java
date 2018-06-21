package com.hf.lesson18;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class BlipCheck implements Externalizable{
	// 注释掉后也可以运行，因为当主方法运行时，实际上就会加载本类的static方法，
	//而构造方法可以看作是静态的，没有构造方法的话虚拟机会自动添加上，而对象序列化恢复的时侯会调用默认构造器，所以调用的是虚拟机自动生成的默认构造器
//	public BlipCheck() {
//		System.out.println("BlipCheck constructor");
//	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("BlipCheck.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("BlipCheck.readExternal");
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Constructing objects");
		Blip4 Blip4 = new Blip4();
		BlipCheck BlipCheck = new BlipCheck();
		
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("C:\\Users\\WIN10\\Desktop\\Blips.out"));
		System.out.println("Saving Objects");
		o.writeObject(Blip4);// 保存的时候会调用writeExternal
		o.writeObject(BlipCheck);
		o.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\WIN10\\Desktop\\Blips.out"));
		System.out.println("Recovering Blip4: ");
		Blip4 = (Blip4)in.readObject();// 读取的时候会先调用公共的默认构造器，然后调用readExternal
		System.out.println("Recovering b2: ");
		BlipCheck = (BlipCheck)in.readObject();//com.hf.lesson18.Blip2; no valid constructor
		in.close();
	}
}
// 实现Externalizable 的类，在恢复时都要调用默认构造器,而且还要显示的写出默认构造器,且构造器必须为public
class Blip4 implements Externalizable {
	
	public Blip4() {
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

