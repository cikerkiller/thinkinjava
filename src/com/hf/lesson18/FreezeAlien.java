package com.hf.lesson18;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FreezeAlien {
	public static void main(String[] args) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\WIN10\\Desktop\\worm.out"));
		Alien a = new Alien();
		out.writeObject(a);
		out.close();
	}
}  
