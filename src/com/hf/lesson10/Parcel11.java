package com.hf.lesson10;

import com.hf.lesson10.Parcel11.ParcelDestination.AnotherLevel;

/**
 * 静态内部类（嵌套类）
 * @author ciker
 * @desc   
 *
 */
public class Parcel11 {
	private static class ParcelContents implements Contents{
		private int i=11;
		@Override
		public int value() {
			return i;
		}
	}
	
	protected static class ParcelDestination implements Destinaton{
		private String label;
		private ParcelDestination(String whereTo) {this.label=whereTo;}
		@Override
		public String readLabel() {
			return label;
		}
		
		public static void f() {}
		static int x=10;
		int a=1;
		
		static class AnotherLevel{
			public static void f() {}
			static int x=10;
			int a=1;
		}
	}
	public static Destinaton destinaton(String s) {
		return new ParcelDestination(s);
	}
	public static Contents contents() {
		return new ParcelContents();
	}
	public static void main(String[] args) {
		Contents contents= contents();
		contents.value();
		Destinaton destinaton = destinaton("ta");
		String readLabel = destinaton.readLabel();
		System.out.println("ParcelDestination.AnotherLevel.x=="+ParcelDestination.AnotherLevel.x);
		int x = ParcelDestination.x;
		System.out.println("ParcelDestination.x=="+x);
		System.out.println("label=="+readLabel);
		AnotherLevel anotherLevel=new AnotherLevel();
		AnotherLevel.f();
		ParcelDestination destination=new ParcelDestination("");
		int a2 = destination.a;
		System.out.println("destination.a=="+destination.a);
		int a = anotherLevel.a;
		System.out.println("anotherLevel.a=="+a);
	}
	
}
