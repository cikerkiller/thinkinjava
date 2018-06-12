package com.hf.lesson10;

public class Percel1 {
	class Contents{
		private int i=11;
		public int value() {return i;}
	}
	class Destination{
		private String label;
		Destination(String whereTo){
			this.label=whereTo;
			System.out.println(this);
		}
		String readLabel() {return label;}
	}
	
	public void ship(String dest) {
		Contents contents=new Contents();
		Destination destination=new Destination(dest);
		System.out.println(destination.readLabel());
	}
	
	public static void main(String[] args) {
		Percel1 percel1=new Percel1();
		percel1.ship("Tasmania");
	}
}
