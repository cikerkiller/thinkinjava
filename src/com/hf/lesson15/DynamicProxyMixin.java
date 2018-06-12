package com.hf.lesson15;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 与动态代理混合
 * @author ciker
 * @desc   
 *
 */
public class DynamicProxyMixin {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		Object instance = MixinProxy.newInstance(new TwoTuple(new BasicImpl(), Basic.class),
					new TwoTuple(new TimeStampedImpl(), TimeStamped.class),
					new TwoTuple(new SerialNumberedImpl(), SerialNumbered.class));
		
		Basic mixin1=(Basic)instance;
		mixin1.set("mixin1");
		TimeStamped mixin2=(TimeStamped)instance;
		SerialNumbered mixin3=(SerialNumbered)instance;
		System.out.println(mixin1.get()+"\n"+mixin2.getStamp()+"\n"+mixin3.getSerialNumber());
		
	}
}

class MixinProxy implements InvocationHandler{
	Map<String, Object> delegatesByMethod;
	@SafeVarargs
	public MixinProxy(TwoTuple<Object, Class<?>>...pairs) {
		delegatesByMethod = new HashMap<>();
		// 注册
		for(TwoTuple<Object, Class<?>> pair:pairs) {
			for(Method m:pair.second.getMethods()) {
				String methodName = m.getName();
				
				if(!delegatesByMethod.containsKey(methodName)) {
					delegatesByMethod.put(methodName, pair.first);
				}
				
			}
		}
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Object delegate = delegatesByMethod.get(methodName);
		return method.invoke(delegate, args);
	}
	
	@SuppressWarnings("unchecked")
	public static Object newInstance(TwoTuple<Object, Class<?>>...pairs) {
		Class<?>[] interfaces = new Class[pairs.length];
		for(int i= 0;i <pairs.length;i++) {
			interfaces[i] = (Class<?>) pairs[i].second;
		}
		ClassLoader cl = pairs[0].getClass().getClassLoader();
		return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
	}
}
