package com.hf.lesson18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StoringAndRecoveringData {
	private static final String filename = "C:\\Users\\WIN10\\Desktop\\2.txt";
	public static void main(String[] args) throws Exception {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
		out.writeDouble(12.5);
		out.writeUTF("this is a test");
		out.writeBoolean(true);
		out.writeInt(15);
		out.close();
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
		System.out.println(in.readDouble());// 按顺序读
		System.out.println(in.readUTF());
		System.out.println(in.readInt());
		System.out.println(in.readBoolean());
		in.close();
	}
}
