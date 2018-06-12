package com.hf.lesson14.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
	private static List<Class<? extends Pet>> types = new ArrayList<>();
	public ForNameCreator(String...strs) {
		loader(strs);
	}
	@SuppressWarnings("unchecked")
	private void loader(String...strs) {
		for(String name : strs) {
			try {
				types.add((Class<? extends Pet>)Class.forName(name));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public List<Class<? extends Pet>> types() {
		return types;
	}
	public static void main(String[] args) {
		ForNameCreator fnc = new ForNameCreator("com.hf.lesson14.pets.EgyptianMau",
				"com.hf.lesson14.pets.Hamster",
				"com.hf.lesson14.pets.Rodent",
				"com.hf.lesson14.pets.Cat");
		while(true)
			System.out.println(fnc.randomPet());
	}
}
