package com.hf.lesson07;

public class Detergent {
	private Cleanser cleanser=new Cleanser();
	Detergent(int i){
		System.out.println("Detergent.."+i);
	}
	public void append(String a) {
		cleanser.append(a);
	}
	public void dilute() {
		append(" dilute() ");
	}
	public void apply() {
		append(" apply() ");
	}
	public void scrub() {
		append(" Detergent.scrub() ");
		cleanser.scrub();
	}
	
	public void foam() {
		append(" foam() ");  
	}
	public String toString() {
		return cleanser.toString();
	}
	public static void main(String[] args) {
		Detergent cleanser=new Detergent(1);
		cleanser.dilute();
		cleanser.apply();
		cleanser.scrub();
		cleanser.foam();
		System.out.println(cleanser);
		Cleanser.main(args);
	}
}
class Cleanser{
	private String s="Cleaner";
	 {
		System.out.println("static Cleaner");
	}
	Cleanser(){
		System.out.println("Cleanser..");
	}
	public void append(String a) {
		s+=a;
	}
	public void dilute() {
		append(" dilute() ");
	}
	public void apply() {
		append(" apply() ");
	}
	public void scrub() {
		append(" scrub() ");
	}
	
	public String toString() {
		return s;
	}
	public static void main(String[] args) {
		Cleanser cleanser=new Cleanser();
		cleanser.dilute();
		cleanser.scrub();
		cleanser.apply();
		System.out.println(cleanser);
	}
}