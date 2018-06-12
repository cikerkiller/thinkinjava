package com.hf.lesson10;

public class Parcel10 {
	public Destinaton destinaton(final String dest,final float price) {
		return new Destinaton() {
			private int cost;
			{
				cost = Math.round(price);
				if(cost>100) {
					System.out.println("Over budget!");
				}
			}
			private String label=dest;
			@Override
			public String readLabel() {
				return label;
			}
		};
	}
	public static void main(String[] args) {
		Parcel10 parcel10=new Parcel10();
		Destinaton destinaton = parcel10.destinaton("ta", 81.1f);
		String readLabel = destinaton.readLabel();
		System.out.println(readLabel);
	}
}
