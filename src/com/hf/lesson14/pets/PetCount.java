package com.hf.lesson14.pets;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hf.lesson14.TypeCounter;

/**
 * 宠物计数器
 * @author ciker
 * @desc   
 *
 */
public class PetCount {
	static class PetCounter extends LinkedHashMap<Class<? extends Pet>,Integer>{
		private static final long serialVersionUID = 6299886515348176679L;

		public PetCounter() {
			super(MapData.map(LiteralPetCreator.allTypes, 0));
		}
		
		public void count(Pet pet) {
			for(Map.Entry<Class<? extends Pet>, Integer> pair :entrySet()) {
				if(pair.getKey().isInstance(pet)) {
					put(pair.getKey(), pair.getValue()+1);
				}
			}
		}
		
		public String toString() {
			StringBuffer sbu=new StringBuffer("{");
			for(Map.Entry<Class<? extends Pet>, Integer> pair :entrySet()) {
				sbu.append(pair.getKey().getSimpleName());
				sbu.append("=");
				sbu.append(pair.getValue());
				sbu.append(", ");
			}
			sbu.delete(sbu.length()-2, sbu.length());
			sbu.append("}");
			return sbu.toString();
		}
	}
	// 对于14.3.2中MapData的实现
	static class MapData{
		private static Map<Class<? extends Pet>,Integer> map =new LinkedHashMap<>();
		public static Map<Class<? extends Pet>,Integer> map(List<Class<? extends Pet>> list,Integer i) {
			if(list!=null&&list.size()>0) {
				for(Class<? extends Pet> o:list) {
					map.put(o, i);
				}
			}
			return map;
		}
	}
	public static void main(String[] args) {  
//		PetCounter petCounter = new PetCounter();
		TypeCounter petCounter=new TypeCounter(Pet.class);
		for(Pet pet: Pets.createArray(20)) {
			System.out.println(pet.getClass().getSimpleName()+" ");
			petCounter.count(pet);
		}
		System.out.println();
		System.out.println(petCounter);
	}
}
