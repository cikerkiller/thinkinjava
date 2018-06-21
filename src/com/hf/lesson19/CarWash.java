package com.hf.lesson19;

import java.util.EnumSet;

public class CarWash {
	public enum Cycle {
		UNDERBODY {

			@Override
			void action() {
				System.out.println("UNDERBODY...");
			}
			
		},
		WHEELWASH {

			@Override
			void action() {
				System.out.println("WHEELWASH...");
				
			}
			
		},
		PREWASH {

			@Override
			void action() {
				System.out.println("PREWASH...");
			}
			
		},
		BASIC {

			@Override
			void action() {
				System.out.println("BASIC...");
			}
			
		},
		HOTWAX {

			@Override
			void action() {
				System.out.println("HOTWAX...");
			}
			
		},
		RINSE {

			@Override
			void action() {
				System.out.println("RINSE...");
			}
			
		},
		BLOWDRY {

			@Override
			void action() {
				System.out.println("BLOWDRY...");
			}
			
		};
		abstract void action();
	}
	
	EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC,Cycle.RINSE);
	
	public void add(Cycle c) {
		cycles.add(c);
	}
	
	public void washCar() {
		for(Cycle c:cycles) {
			c.action();
		}
	}
	
	public String toString() {
		return cycles.toString();
	}
	
	public static void main(String[] args) {
		CarWash wash = new CarWash();
		System.out.println(wash);
		wash.washCar();
		
		wash.add(Cycle.UNDERBODY);// 向set中添加的顺序不重要，输出的次序取决于enum实例定义时的顺序
		wash.washCar();
		
		
	}
	
}
