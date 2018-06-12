package com.hf.lesson09;

public abstract class AbstractPlan {
	public static void main(String[] args) {
		
	}
}
class Plan extends AbstractPlan{
	public void f() {}
	public static void ff(AbstractPlan plan) {
		Plan plan2=(Plan)plan;
		ff(plan2);  
	}
}