package com.hf.lesson15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hf.lesson14.pets.Cat;
import com.hf.lesson14.pets.Dog;
import com.hf.lesson14.pets.Pet;

/**
 * 动态类型安全检查
 * 
 * @author ciker
 * @desc
 *
 */
public class CheckedList {
	static void oldStyleMethod(List probableDogs) {
		probableDogs.add(new Cat());
	}

	public static void main(String[] args) {
		List<Dog> dogs = new ArrayList<>();
		oldStyleMethod(dogs);

		// List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);//
		// 类型检查 异常
		// oldStyleMethod(dogs2);

		List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
		pets.add(new Dog());
		pets.add(new Cat());
	}
}
