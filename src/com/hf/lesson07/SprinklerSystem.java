package com.hf.lesson07;

public class SprinklerSystem {
	private String value1,value2,value3,value4;
	private WaterSource source=new WaterSource();
	private int i;
	static {
		System.out.println("static ");
	}
	private float f;
	public SprinklerSystem(){
		System.out.println("SprinklerSystem()");
	}
	@Override
	public String toString() {
		return "value1="+value1+" "+
				"value2="+value2+" "+
				"value3="+value3+" "+
				"value4="+value4+"\n"+
				"i="+i+" "+"f="+f+" "+
				"source="+source;
	}
	public static void main(String[] args) {
		SprinklerSystem sprinklerSystem=new SprinklerSystem();
		System.out.println(sprinklerSystem);
	}
	
}
class WaterSource{
	private String s;
	WaterSource(){
		System.out.println("WaterSource()");
		s="Constru";
	}
	@Override
	public String toString() {
		return s;
	}
	
}