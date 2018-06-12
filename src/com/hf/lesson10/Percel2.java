package com.hf.lesson10;

public class Percel2 {
	private class Contents{
		private int i=11;
		public int value() {return i;}
	}
	class Destination{
		private String label;
		Destination(String whereTo){
			this.label=whereTo;
		}
		String readLabel() {return label;}
	}
	
	public Destination to(String s) { return new Destination(s);}
	public Contents contents() {return new Contents();}
	public void ship(String dest) {
		Contents contents=contents();
		Destination destination=to(dest);
		System.out.println(destination.readLabel());
	}
	Contents contents=new Contents();
	public static void main(String[] args) {
		Percel2 p=new Percel2();
		p.ship("Tasmania");
		Percel2 q=new Percel2();
		Contents contents=q.new Contents();
		System.out.println(contents.i);
		Destination destination=q.to("Borneo");
	}
	void f() {
		Percel2 q=new Percel2();
		Contents contents=q.contents();
	}
}
