package com.hf.lesson14;

import java.util.HashMap;
import java.util.Map;

public class TypeCounter extends HashMap<Class<?>, Integer>{
	private Class<?> baseType;
	
	public TypeCounter(Class<?> baseType) {this.baseType=baseType;}
	
	public void count(Object obj) {
		Class<?> type =obj.getClass();
		if(!baseType.isAssignableFrom(type)) {
			throw new RuntimeException(obj+" incorrect type: "+type+", should be type or subtype of "+baseType);
		}
		countClass(type);
	}
	
	private void countClass(Class<?> type) {
		Integer quantity = get(type);
		put(type, quantity == null ? 1 : quantity+1);
		Class<?> superclass = type.getSuperclass();
		if(superclass != null && baseType.isAssignableFrom(superclass)) {
			countClass(superclass);
		}
	}
	
	public String toString() {
		StringBuffer sbu=new StringBuffer("{");
		for(Map.Entry<Class<?>, Integer> pair :entrySet()) {
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
