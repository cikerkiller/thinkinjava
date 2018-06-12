package com.hf.lesson14;

/**
 * 动态代理
 * @author ciker
 * @desc   
 *
 */
public class SimpleProxyDome {
	
	public static void consumer(Interface in) {
		in.doSomething();
		in.somethingElse("bonobo");
	}
	
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}
}
interface Interface{
	void doSomething();
	void somethingElse(String arg);
}
class RealObject implements Interface{

	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("somethingElse "+arg);
	}
}
class SimpleProxy implements Interface{
	private Interface in;
	public  SimpleProxy(Interface in) {
		this.in=in;
	}
	@Override
	public void doSomething() {
		in.doSomething();
	}

	@Override
	public void somethingElse(String arg) {
		in.somethingElse(arg);
	}
	
}


