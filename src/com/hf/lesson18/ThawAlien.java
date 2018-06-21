package com.hf.lesson18;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ThawAlien {
	public static void main(String[] args) throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\WIN10\\Desktop\\worm.out"));
		Object object = in.readObject();// 读取序列化对象
		System.out.println(object.getClass().getSimpleName());
		in.close();
	}
}
