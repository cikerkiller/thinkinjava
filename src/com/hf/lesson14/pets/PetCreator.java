package com.hf.lesson14.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * 模板方法设计模式变体
 * @author ciker
 * @desc   
 *
 */
public abstract class PetCreator {
	private Random rand = new Random(47);
	public abstract List<Class<? extends Pet>> types();
	// 随机生成pet
	public Pet randomPet() {
		int n = rand.nextInt(types().size());// 定义随机最大值为pet集合的大小-1
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Pet[] crateArray(int size) {
		Pet[] result = new Pet[size];
		for(int i=0;i<size;i++) {
			result[i]=randomPet();
		}
		return result;
	}
	
	public ArrayList<Pet> arrayList(int size){
		ArrayList<Pet> result =new ArrayList<>();
		Collections.addAll(result, crateArray(size));
		return result;
	}
}
