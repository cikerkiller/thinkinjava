package com.hf.lesson20;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {
	public static void trackUserCases(List<Integer> useCases, Class<?> cl) {
		// 遍历cl中的公共方法
		for(Method m : cl.getDeclaredMethods()) {
			// 查看方法上是否有注解
			UseCase uc = m.getAnnotation(UseCase.class);
			if(uc != null) {
				System.out.println("Found Use Case: "+uc.id()+" "+uc.description());
				// 有注解的移除集合中的id
				useCases.remove(new Integer(uc.id()));
			}
		}
		
		for(int i : useCases) {
			System.out.println("Warning: missing use case-"+i);
		}
		
	}
	
	public static void main(String[] args) {
		List<Integer> userCases = new ArrayList<>();
		Collections.addAll(userCases, 47,48,49,50,51);
		trackUserCases(userCases, PasswordUtils.class);
	}
}
