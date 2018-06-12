package com.hf.lesson15;

/**
 * 协变数组
 * 
 * @author ciker
 * @desc
 *
 */
public class CovariantArrays {
	public static void main(String[] args) {
		Fruit[] fruit = new Apple[10];
		fruit[0] = new Apple();
		fruit[1] = new Jonathan();
		fruit[0] = new Fruit();// 编译期通过检查，运行时抛出异常 ArrayStoreException
		// 不能把涉及Apple的泛型赋给涉及Fruit的泛型
		// List<Fruit> list = new ArrayList<Apple>(); // 编译期不通过，
	}
}

class Fruit {

}

class Apple extends Fruit {
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}