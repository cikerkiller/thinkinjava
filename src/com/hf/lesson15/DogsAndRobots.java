package com.hf.lesson15;

import com.hf.lesson14.pets.Dog;

/**
 * 潜在类型机制,只要求实现某个方法子集（只要都有那个方法）,可以横跨类继承结构调用不属于公共接口的方法（如Python与c++）
 * @author ciker
 * @desc   
 *
 */
public class DogsAndRobots {
	public static void main(String[] args) {
		Communicate.perform(new PerformingDog());
		Communicate.perform(new Robot());
	}
}
interface Performs{
	void speak();
	void sit();
}

class PerformingDog extends Dog implements Performs{

	@Override
	public void speak() {
		System.out.println("Woof!");
		
	}

	@Override
	public void sit() {
		System.out.println("Sitting");
	}
	
	public void reproduce() {}
	
}

class Robot implements Performs{

	@Override
	public void speak() {
		System.out.println("Click!");
	}

	@Override
	public void sit() {
		System.out.println("Clank!");
	}
	public void oliChange() {}
}

class Communicate{
	public static <T extends Performs> void perform(T performer) {
		performer.speak();
		performer.sit();
	}
	// 泛型不是必须的，这些类已经被强制要求实现Performs
//	public static void perform(Performs performer) {
//		performer.speak();
//		performer.sit();
//	}
}