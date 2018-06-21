package com.hf.lesson18;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
// Externalizable 的替代方式
public class SerialCtl implements Serializable{
	private String a;
	private transient String b;
	public SerialCtl(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString() {
		return "SerialCtl [a=" + a + ", b=" + b + "]";
	}
	
	private void writeObject(ObjectOutputStream out) throws Exception{
		out.defaultWriteObject();// 非transient字段需要调用默认的
		out.writeObject(b);// transient字段若需要序列化的话需要显示去处理
		
	}
	
	private void readObject(ObjectInputStream in) throws Exception{
		in.defaultReadObject();
		b=(String)in.readObject();
	}
	
	public static void main(String[] args) throws Exception{
		SerialCtl s= new SerialCtl("a", "b");
		System.out.println(s);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(buf);
		out.writeObject(s);
		out.close();
		TimeUnit.SECONDS.sleep(1);
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
		s=(SerialCtl)in.readObject();
		System.out.println(s);
	}
}
