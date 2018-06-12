package com.hf.lesson11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hf.lesson14.pets.Cat;
import com.hf.lesson14.pets.Cymric;
import com.hf.lesson14.pets.Dog;
import com.hf.lesson14.pets.Mutt;
import com.hf.lesson14.pets.Person;
import com.hf.lesson14.pets.Pet;

public class MapOfList {
	public static Map<Person, List<? extends Pet>> petPeople  = new HashMap<>();
	static {
		petPeople.put(new Person("Dawn"), Arrays.asList(new Cymric("Molly"),new Mutt("Spot")));
		petPeople.put(new Person("kate"), Arrays.asList(new Cat("Shackleton"),new Cat("Elsie May"),new Dog("Margrett")));
	}
	
}
