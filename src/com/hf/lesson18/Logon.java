package com.hf.lesson18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Logon implements Serializable{
	private Date date = new Date();
	private String username;
	private transient String password;// 使用transient 让不想序列化的字段不进行序列化
	public Logon(String username, String password) {
		this.username=username;
		this.password=password;
	}
	@Override
	public String toString() {
		return "Logon [date=" + date + ", username=" + username + ", password=" + password + "]";
	}
	
	public static void main(String[] args) throws Exception{
		Logon a = new Logon("hf", "123");
		System.out.println(a);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\WIN10\\Desktop\\pass.txt"));
		out.writeObject(a);
		out.close();
		TimeUnit.SECONDS.sleep(1);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\WIN10\\Desktop\\pass.txt"));
		a=(Logon)in.readObject();
		System.out.println(a);
		in.close();
	}
}
