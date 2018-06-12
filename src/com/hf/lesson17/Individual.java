package com.hf.lesson17;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.hf.lesson11.MapOfList;
import com.hf.lesson14.pets.Pet;

public class Individual implements Comparable<Individual>{
	
	private static long counter = 0;
	private final long id = counter++;
	private String name;
	public  Individual(String name) {
		this.name=name;
	}
	public Individual() {
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ( name == null ? "" : " " + name);
	}
	
	public long id() {return id;}
	public boolean equals(Object o) {
		return o instanceof Individual && id == ((Individual)o).id;
	}
	
	public int hashCode() {
		int result = 17;
		if(name!=null) {
			result = 37*result + name.hashCode();
		}
		result = 37 * result + (int)id;
		return result;
	}
	@Override
	public int compareTo(Individual o) {
		String first = getClass().getSimpleName();
		String oFirst = o.getClass().getSimpleName();
		int firstCompare = first.compareTo(oFirst);
		if(firstCompare!=0) {
			return firstCompare;
		}
		if(name!=null && o.name != null) {
			int secondCompare = name.compareTo(o.name);
			if(secondCompare!=0) {
				return secondCompare;
			}
		}
		return (o.id < id ? -1 : (o.id == id ? 0 : 1));
	}
	
	public static void main(String[] args) {
		Set<Individual> pets = new TreeSet<>();
		for(List<? extends Pet> lp : MapOfList.petPeople.values()) {
			
			for(Pet p :lp) {
				pets.add(p);
			}
		}
		System.out.println(pets);
	}
	
}
