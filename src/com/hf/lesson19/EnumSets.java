package com.hf.lesson19;

import java.util.EnumSet;
// 使用enumset替代标志
public class EnumSets {
	public static void main(String[] args) {
		EnumSet<AlarmPoints> points =EnumSet.noneOf(AlarmPoints.class);
		points.add(AlarmPoints.BATHROOM);
		System.out.println(points);
		points.addAll(EnumSet.of(AlarmPoints.OFFICE1, AlarmPoints.OFFICE2, AlarmPoints.OFFICE3));
		System.out.println(points);
		points = EnumSet.allOf(AlarmPoints.class);
		points.removeAll(EnumSet.range(AlarmPoints.OFFICE1, AlarmPoints.OFFICE3));
		System.out.println(points);
		points = EnumSet.complementOf(points);
		System.out.println(points);
	}
}
enum AlarmPoints {
	STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY, KITCHEN
}

