package com.hf.lesson15;

import java.lang.reflect.Method;

/**
 * 使用反射实现潜在类型机制
 * @author ciker
 * @desc   
 *
 */
public class LatentReflection {
	public static void main(String[] args) {
		CommunicateReflectively.perform(new Mime());
		CommunicateReflectively.perform(new SmartDog());
		CommunicateReflectively.perform(new Robot());
	}
}

class Mime{
	public void walkAgainstTheWind() {}
	public void sit() {
		System.out.println("Mime.sit");
	}
	public void speak() {
		System.out.println("Mime.speak");
	}
}
class SmartDog{
	public void speak() {System.out.println("SmartDog.speak");}
	public void sit() {System.out.println("SmartDog.sit");}
}


class CommunicateReflectively{
	public static void perform(Object speaker) {
		Class<? extends Object> class1 = speaker.getClass();
		try {
			Method method = class1.getMethod("speak");
			method.invoke(speaker);
		} catch (Exception e) {
			System.out.println("speak error");
			throw new RuntimeException(e);
		}
		
		try {
			Method method = class1.getMethod("sit");
			method.invoke(speaker);
		} catch (Exception e) {
			System.out.println("sit error");
			throw new RuntimeException(e);
		}
		
	}
}