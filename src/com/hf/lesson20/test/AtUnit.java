package com.hf.lesson20.test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.hf.lesson18.BinaryFile;
import com.hf.lesson18.ProcessFiles;
import com.hf.lesson18.ProcessFiles.Strategy;

public class AtUnit implements Strategy{
	
	static Class<?> testClass;
	static List<String> failedTests = new ArrayList<>();
	static long testsRun = 0;
	static long failures = 0;
	
	@Override
	public void process(File file) {
		try {
			String cName = ClassNameFinder.thisClass(BinaryFile.read(file));
			if(!cName.contains(".")) {
				return;
			}
			testClass = Class.forName(cName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		TestMethods testMethods = new TestMethods();
		Method creator = null;
		Method cleanup = null;
		for(Method m : testClass.getDeclaredMethods()) {
			testMethods.addIfTestMethod(m);
			if(creator == null) {
				creator = checkForCreatorMethod(m);
			}
			if(cleanup == null) {
				cleanup = checkForCleanupMethod(m);
			}
		}
		if(testMethods.size() > 0) {
			if(creator == null) {
				try {
					if(!Modifier.isPublic(testClass.getDeclaredConstructor().getModifiers())) {
						System.out.print("Error: "+testClass + " default constructor must be public");
						System.exit(1);
					}
				} catch (Exception e) {
					throw new RuntimeException();
				} 
			}
			System.out.println(testClass.getName());
		}
		
		for(Method m : testMethods) {
			System.out.println("  . "+m.getName() + " ");
			try {
				Object testObject = createTestObject(creator);
				boolean success = false;
				try {
					if(m.getReturnType().equals(boolean.class)) {
						success = (Boolean)m.invoke(testObject);
					}else {
						m.invoke(testObject);
						success = true;
					}
				} catch (Exception e) {
					System.out.print(e.getCause());
				}
				
				System.out.print(success ? "" : "(failed)");
				testsRun++;
				if(!success) {
					failures++;
					failedTests.add(testClass.getName() + ": " + m.getName());
				}
				if(cleanup != null) {
					cleanup.invoke(testObject, testObject);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
		
		
	}
	static class TestMethods extends ArrayList<Method> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2989448950963876005L;

		void addIfTestMethod(Method m) {
			if(m.getAnnotation(Test.class) == null) {
				return;
			}
			if(!(m.getReturnType().equals(boolean.class) || m.getReturnType().equals(void.class))) {
				throw new RuntimeException("@test method must return boolean or void");
			}
			m.setAccessible(true);
			add(m);
		}
	}
	
	private static Method checkForCreatorMethod(Method m) {
		if(m.getAnnotation(TestObjectCreate.class) == null) {
			return null;
		}
		if(!m.getReturnType().equals(testClass)) {
			throw new RuntimeException("@TestObjectCreate must return instance of Class to be tested");
		}
		if((m.getModifiers() & Modifier.STATIC) < 1) {
			throw new RuntimeException("@TestObjectCreate must be static");
		}
		m.setAccessible(true);
		return m;
	}
	private static Method checkForCleanupMethod(Method m) {
		if(m.getAnnotation(TestObjectCleanup.class) == null) {
			return null;
		}
		if(!m.getReturnType().equals(void.class)) {
			throw new RuntimeException("@TestObjectCleanup must return void");
		}
		if((m.getModifiers() & Modifier.STATIC) < 1) {
			throw new RuntimeException("@TestObjectCleanup must be static");
		}
		if(m.getParameterTypes().length == 0 || m.getParameterTypes()[0] != testClass) {
			throw new RuntimeException("@TestObjectCleanup must take an argument of the tested type.");
		}
		m.setAccessible(true);
		return m;
	}
	
	private static Object createTestObject(Method creator) {
		if(creator == null) {
			try {
				return testClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("could`t create a test Object. try using a @testObject method");
			}
		}else {
			try {
				return creator.invoke(testClass);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException("Couldn`t run @TestObject (creator) method");
			}
		}
	}
	
	
	public static void main(String[] args) {
//		args = new String[] {"D:\\hf\\eclipse-workspace\\ThinkInJava\\bin\\com\\hf\\lesson20\\test\\AtUnitExample"};
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
		new ProcessFiles(new AtUnit(), "class").start(args);
		if(failures == 0) {
			System.out.print("OK ("+ testsRun +" tests)");
		}else {
			System.out.print("(" + testsRun + " tests)");
			System.out.print("\n>>> " + failures + "FAILURE" + (failures > 1 ? "S" : "") + "<<<" );
			for(String failed : failedTests) {
				System.out.println(" "+failed);
			}
		}
	}
	
}
