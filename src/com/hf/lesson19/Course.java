package com.hf.lesson19;
import com.hf.lesson19.Food.Appetizer;
import com.hf.lesson19.Food.Coffee;
import com.hf.lesson19.Food.Dessert;
import com.hf.lesson19.Food.MainCourse;
// 随机生成一份菜单
public enum Course {
	APPETIZER(Appetizer.class),
	MAINCOOURSE(MainCourse.class),
	DESSERT(Dessert.class),
	COFFEE(Coffee.class);
	private Food[] values;
	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}
	public Food randomSelection() {
		return Enums.random(values);
	}
	public static void main(String[] args) {
		for(Course course : Course.values()) {
			Food food = course.randomSelection();
			System.out.println(food);
		}
	}
}
