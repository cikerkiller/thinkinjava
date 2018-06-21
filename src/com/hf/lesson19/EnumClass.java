package com.hf.lesson19;

public class EnumClass {
	public static void main(String[] args) {
		for(Shrubbery s:Shrubbery.values()) {
			System.out.println(s+" ordinal: "+s.ordinal());
			System.out.println(s.compareTo(Shrubbery.CRAWLINNG));
			System.out.println(s.equals(Shrubbery.CRAWLINNG));
			System.out.println(Shrubbery.CRAWLINNG.toString());
			System.out.println(s.getClass().getSimpleName());
			
			System.out.println(s.name());
		}
		
		for(String s: "GROUND CRAWLINNG HANGING".split(" ")) {
			Shrubbery sh =Enum.valueOf(Shrubbery.class, s);
			System.out.println(sh);
		}
		
	}
}
enum Shrubbery{
	GROUND, CRAWLINNG, HANGING
}