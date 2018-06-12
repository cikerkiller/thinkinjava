package com.hf.lesson17.test;

import java.util.List;

public class Tester<C> {
	public static int fieldWidth = 10;
	public static TestParam[] defaultParams = TestParam.array(10,5000,100,5000,1000,5000,10000,500);
	
	protected C container;
	protected C initalize(int size) {
		return container;
	}
	
	private String headline = "";
	private List<Test<C>> tests;
	private static String stringField() {
		return "%" + fieldWidth+"s";
	}
	private static String numberield() {
		return "%"+fieldWidth+"d";
	}
	private static int sizeWidth = 10;
	private static String sizeField = "%"+sizeWidth+"s";
	private TestParam[] paramList =  defaultParams;
	public Tester(C container,List<Test<C>> tests) {
		this.container = container;
		this.tests=tests;
		if(container!=null) {
			headline = container.getClass().getSimpleName();
		}
	}
	public Tester(C container,List<Test<C>> tests,TestParam[] paramList) {
		this(container, tests);
		this.paramList=paramList;
	}
	public void setHeadLine(String newHeadLine) {
		this.headline=newHeadLine;
	}
	
	public static <C> void run(C cntnr,List<Test<C>> tests) {
		new Tester<>(cntnr, tests).timedTest();
	}
	
	public static <C> void run(C container,List<Test<C>> tests,TestParam[] paramList) {
		new Tester<>(container, tests, paramList).timedTest();
	}
	
	private void displayHeader() {
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headline.length() - 1;
		StringBuilder head = new StringBuilder(width);
		for(int i=0;i<dashLength/2;i++) {
			head.append('-');
		}
		head.append(' ');
		head.append(headline);
		head.append(' ');
		for(int i=0;i<dashLength/2;i++) {
			head.append('-');
		}
		System.out.println(head);
		System.out.format(sizeField,"size");
		for(Test<C> test : tests) {
			System.out.format(stringField(),test.name);
		}
		System.out.println();
	}
	
	public void timedTest() {
		displayHeader();
		for(TestParam param : paramList) {
			System.out.format(sizeField,param.size);
			for(Test<C> test : tests) {
				C kontainer = initalize(param.size);
				long start = System.nanoTime();
				// 循环次数
				int reps = test.test(kontainer, param);
				long duration = System.nanoTime()-start;
				long timePerRep = duration / reps;
				System.out.format(numberield(), timePerRep);	
			}
			System.out.println();
		}
	}
}
