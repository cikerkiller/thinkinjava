package com.hf.lesson08;

public class Sandwich extends PortableLunch{
	private Bread bread=new Bread();
	private Cheese cheese=new Cheese();
	private Lettuce lettuce=new Lettuce();
	public Sandwich() {System.out.println("Sandwich..");}
	public static void main(String[] args) {
		new Sandwich();
		/**
		 * 1、调用本类构造器时先调用基类构造器，从最顶层的基类开始Meal-》Lunch-》PortableLunch
		 * 
		 * 2、成员变量初始化，从最开始定义的变量开始 Bread-》Cheese-》Lettuce
		 * 
		 * 3、调用本类构造器
		 * 
		 * 
		 * */
	}
}
class Meal{
	Meal(){System.out.println("Meal..");}
}
class Bread{
	Bread(){System.out.println("Bread..");}
}

class Cheese{
	Cheese(){System.out.println("Cheese..");}
}

class Lettuce{
	Lettuce(){System.out.println("Lettuce..");}
}

class Lunch extends Meal{
	Lunch(){System.out.println("Lunch..");}
}

class PortableLunch extends Lunch
{
	PortableLunch(){System.out.println("PortableLunch..");}
}