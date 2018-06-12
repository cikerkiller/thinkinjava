package com.hf.lesson15;

import java.util.List;
import java.util.Map;

import com.hf.lesson14.pets.Person;
import com.hf.lesson14.pets.Pet;

/**
 *  类型参数推断：使用泛型方法时，通常不必指名参数类型，编译器会为我们找出具体的类型。只对赋值操作有效，其他时候不起作用
 * @author ciker
 * @desc   
 *
 */
public class LimitsOfInterface {
	static void f(Map<Person,List<? extends Pet>> petPeople) {
		
	}
	public static void main(String[] args) {
//		f(New.map()); not compile 调用泛型方法后，其返回值被赋给一个Object类型变量
		f(New.<Person,List<? extends Pet>>map());// 显示的类型说明,在编写非赋值语句时需要
	}
}
