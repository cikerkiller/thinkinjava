package com.hf.lesson14.nullobj;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

public class NullRobot {
	public static Robot newNullRobot(Class<? extends Robot> type) {
		// 动态代理获取nullRobot信息
		return (Robot) Proxy.newProxyInstance(
					NullRobot.class.getClassLoader(),
					new Class[] {Null.class,Robot.class},
					new NullRobotProxyHandler(type));
	}
	public static void main(String[] args) {
		Robot[] bots = {new SnowRemovalRobot("SnowBee"),newNullRobot(SnowRemovalRobot.class)};
		
		
		for(Robot robot : bots) {
			Robot.Test.test(robot);
		}
		
	}
}

/**
 * 空robot调用处理器
 * @author ciker
 * @desc   
 *
 */
class NullRobotProxyHandler implements InvocationHandler{
	private String nullName;
	
	private Robot proxied = new NRobot();
	
	public NullRobotProxyHandler(Class<? extends Robot> type) {
		nullName = type.getSimpleName()+" NullRobot";
	}
	
	private class NRobot implements Null,Robot{

		@Override
		public String name() {
			return nullName;
		}

		@Override
		public String model() {
			return nullName;
		}

		@Override
		public List<Operation> operations() {
			return Collections.emptyList();
		}
		
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(proxied, args);
	}
	
}