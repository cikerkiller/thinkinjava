package com.hf.lesson10;

/**
 * 
 * @author ciker
 * @desc 局部内部类   
 *
 */
public class Parcel5 {
	public Destinaton destinaton(String s) {
		class PDestination implements Destinaton{
			private String label;
			
			private PDestination(String whereTo) {
				this.label=whereTo;
			}
			@Override
			public String readLabel() {
				return label;
			}
		}
		return new PDestination(s);
	}
	
	public static void main(String[] args) {
		Parcel5 parcel5=new Parcel5();
		Destinaton destinaton = parcel5.destinaton("Tasmania");
		System.out.println(destinaton.readLabel());
	}
	
}
