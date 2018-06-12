package com.hf.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Gerbil {
	int gerbilNumber;
	public Gerbil(int gerbilNumber) {
		this.gerbilNumber=gerbilNumber;
	}
	public void hop() {
		System.out.println("跳跃的沙鼠："+gerbilNumber);
	}
	public static void main(String[] args) {
		List<Gerbil> list=new ArrayList<>();
		for(int i=0;i<5;i++)
			list.add(new Gerbil(i));
		for(int i=0;i<5;i++)
			list.get(i).hop();
	}
}
