package com.hf.lesson14;

public class GenericToyTest {
	public static void main(String[] args) throws Exception {
		Class<FancyToy> ftClass = FancyToy.class;
		FancyToy newInstance = ftClass.newInstance();
		Class<? super FancyToy> superclass = ftClass.getSuperclass();
		Object newInstance2 = superclass.newInstance();
		
		
	}
}
