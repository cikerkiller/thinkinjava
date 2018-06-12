package com.hf.lesson14.pets;

import java.util.ArrayList;

public class Pets {
	public static final PetCreator creator = new LiteralPetCreator();
	public static Pet randomPet() {
		return creator.randomPet();
	}
	
	public static Pet[] createArray(int size) {
		return creator.crateArray(size);
	}
	public static ArrayList<Pet> arrayList(int size){
		return creator.arrayList(size);
	}
	public static void main(String[] args) {
		System.out.println(arrayList(5));
	}
}
