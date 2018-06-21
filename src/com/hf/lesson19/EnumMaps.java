package com.hf.lesson19;

import java.util.EnumMap;
import java.util.Map;
// 命令设计模式,为每个实例添加不同的行为action
public class EnumMaps {
	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
		em.put(AlarmPoints.BATHROOM, new Command() {
			
			@Override
			public void action() {
				System.out.println("bathroom");
			}
		});
		em.put(AlarmPoints.BATHROOM, new Command() {
			
			@Override
			public void action() {
				System.out.println("bathroom");
			}
		});
		em.put(AlarmPoints.KITCHEN, new Command() {
			
			@Override
			public void action() {
				System.out.println("kitchen");
			}
		});
		
		for(Map.Entry<AlarmPoints, Command> e :em.entrySet()) {
			System.out.println(e.getKey());
			e.getValue().action();
		}
		
		em.get(AlarmPoints.OFFICE1).action();
	}
}
interface Command {
	void action();
}
