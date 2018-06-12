package com.hf.lesson10;

public class TestPercel {
	public static void main(String[] args) {
		Percel4 percel4=new Percel4();
		Contents contents=percel4.contents();
		Destinaton destinaton=percel4.destinaton("Tasmania");
		
	}
}
class Percel4{
	private class PContents implements Contents{
		private int i=11;
		@Override
		public int value() {
			return i;
		}
	}
	
	protected class PDestination implements Destinaton{
		private String label;
		private PDestination(String whereTo) {
			this.label=whereTo;
		}
		@Override
		public String readLabel() {
			return label;
		}
	}
	public Destinaton destinaton(String s) {return new PDestination(s);}
	public Contents contents() {return new PContents();}
}
interface Destinaton{
	String readLabel();
}
interface Contents{
	int value();
}