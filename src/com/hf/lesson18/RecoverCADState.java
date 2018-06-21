package com.hf.lesson18;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {
	public static void main(String[] args) throws Exception  {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\WIN10\\Desktop\\CADState.out"));
		List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>)in.readObject();// 必须维护写入序列化文件以及读取的顺序
		Line.deserializeStaticState(in);// 静态字段必须手动序列与反序列
		List<Shape> shapes =(List<Shape>)in.readObject();
		System.out.println(shapes);
	}
}
