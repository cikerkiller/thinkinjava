package com.hf.lesson15;

/**
 * 自限定 SelfBouned<T extends SelfBouned<T>> 自限定限制只能强制作用于继承关系
 * 
 * @author ciker
 * @desc
 *
 */
public class SlefBounding {
	public static void main(String[] args) {
		A a = new A();
		a.set(new A());
		SelfBouned<A> selfBouned = a.set(new A());
		A aa = (A) a.set(new A());
		System.out.println(new SelfBouned().set(new A()).get().getClass().getSimpleName());
		a = a.set(new A()).get();
		a = a.get();
		C c = new C();
		c = c.setAndGet(new C());

	}
}

class SelfBouned<T extends SelfBouned<T>> {
	T element;

	SelfBouned<T> set(T arg) {
		element = arg;
		return this;
	}

	T get() {
		return element;
	}
}

class A extends SelfBouned<A> {
}

class B extends SelfBouned<A> {
}

class C extends SelfBouned<C> {
	C setAndGet(C arg) {
		set(arg);
		return get();
	}
}

class D {
}
// class E extends SelfBouned<D>{}// 不能编译，不能使用不是SelfBouned的类型参数，即D
// 不是从SelfBouned中导出的，而SlefBouned<T extends SelfBouned<T>> 即类型参数得是SlefBouned的导出类

class F extends SelfBouned {
}
