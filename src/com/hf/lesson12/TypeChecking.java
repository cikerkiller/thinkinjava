package com.hf.lesson12;
/**
 * 类型检查
 * 静态类型检查：基于程序的源代码来验证类型安全的过程
 * 动态类型检查：在程序运行期间来验证类型安全的过程
 * @author ciker
 * @desc   
 *
 */

public class TypeChecking {
	public static void main(String[] args) {
		AA a = new BB();
//		a.me().doB();
		((BB)a.me()).doB();// 可以强转
		((CC)a.me()).doBad();// 可以强转，通过静态类型检查，但是运行会报异常
		a.me().doA();
	}
}

class AA{
	AA me() {
		return this;
	}
	void doA() {
		System.out.println("this is a A");
	}
}

class BB extends AA {
	void doB() {
		System.out.println("this is a B");
	}
}

class CC extends AA{
    public void doBad() {
        System.out.println("Do C");
    }
}