package com.hf.lesson14;
import static  java.lang.System.out;
public class SweetShop {
	public static void main(String[] args) {
		out.println("inside main");
		new Candy();
		out.println("After creating Candy");
		try {
//			new Gum();
			Class.forName("com.hf.lesson14.Gum");
		} catch (ClassNotFoundException e) {
			out.println("Couldn`t find Gum ");
		}
		out.println("After Class.forName(\"Gum\")");
		new Cookie();
		out.print("After creating Cookie");
	}
}

class Candy{
	static {out.println("Loading Candy");}
}
class Gum{
	static {out.println("Loading Gum");}
}
class Cookie{
	static {out.println("Loading Cookie");}
}
