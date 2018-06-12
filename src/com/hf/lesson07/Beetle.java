package com.hf.lesson07;

public class Beetle extends Insect{
	private int k=printInit("Beetle.k init");
	public Beetle() {
		System.out.println("k=="+k);
		System.out.println("j=="+j);
	}
	
	private static int x2=printInit("Beelte.x2 init");
	public static void main(String[] args) {
		Beetle beetle=new Beetle();
	}
}
class Insect{
	private int i=9;
	protected int j;
	Insect(){
		System.out.println("i="+i+" , j="+j);
		  
	}
	private static int x1=printInit("static Insect.x1 init");
	static int printInit(String s) {
		System.out.println("s=="+s);
		return 47;
	}
}