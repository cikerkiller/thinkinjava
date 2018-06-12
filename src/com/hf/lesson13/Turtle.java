package com.hf.lesson13;

import java.io.PrintStream;
import java.util.Formatter;

public class Turtle {
	private String name;
	private Formatter f;
	public Turtle(String name, Formatter f) {
		this.name = name;
		this.f = f;
	}
	public void move(int x,int y) {
		f.format("%s %d , %d\n",name, x,y);
	}
	public static void main(String[] args) {
		PrintStream out = System.out;
		Turtle turtle=new Turtle("tom", new Formatter(System.out));
		turtle.move(1, 4);
		Turtle turtl1e=new Turtle("tom", new Formatter(out));
		turtl1e.move(2, 6);
		turtle=new Turtle("tom", new Formatter(System.err));
		turtle.move(4, 4);
	}
	
}
