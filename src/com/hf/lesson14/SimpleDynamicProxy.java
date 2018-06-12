package com.hf.lesson14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class SimpleDynamicProxy {
	public static void consumer(Interface in) {
		in.doSomething();
		in.somethingElse("bonobo");
	}
	
	public static void main(String[] args) {
		consumer(new RealObject());
		Interface in = (Interface)Proxy.newProxyInstance(SimpleDynamicProxy.class.getClassLoader(), new Class[] {Interface.class}, new DynamicProxyHandler(new RealObject()));
		InvocationHandler handler=Proxy.getInvocationHandler(in);// 返回调用处理器类
		if(handler instanceof DynamicProxyHandler) {
			System.out.println("DynamicProxyHandler ....");
		}
		consumer(in);
	}
}
class DynamicProxyHandler implements InvocationHandler{
	private Object proxy;
	public DynamicProxyHandler(Object proxy) {
		this.proxy=proxy;
	}
	/**
	 * Object proxy 动态代理对象
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// proxy 运行过程中创建的代理对象
		if(proxy instanceof Interface) {
			System.out.println("==========");
		}
		// 每次调用代理对象的方法时就会调用invoke方法，打印代理对象相当于调用toString()方法，而调用方法又会进入invoke方法，这样会出现无限递归
//		System.out.println(proxy);
		System.out.println("method: "+method+" , args :"+(args==null?"":args.length==0?"":Arrays.asList(args)));
		return method.invoke(this.proxy, args);
	}
}