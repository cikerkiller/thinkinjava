package com.hf.lesson12;

public class Switch extends A{
	public Switch() {}
	private boolean state = false;
	public boolean read() {return state;}
	public void on() {state=true;System.out.println(this);}
	public void off() {state=false;System.out.println(this);}
	public String toString() {return state?"on":"off";}
	public static void main(String[] args) {
//		System.out.println("s");
		Switch switch1=new Switch();
	}
}
class A{
	A() throws NullPointerException{ throw new NullPointerException();}
}