package com.hf.lesson15;

import static com.hf.lesson15.Sets.*;
import static com.hf.lesson15.Watercolors.*;

import java.util.EnumSet;
import java.util.Set;

public class WatercolorSets {
	public static void main(String[] args) {
		Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, VIOLET);
		Set<Watercolors> set2 = EnumSet.range(BRILLIANT_RED, PERMANENT_GREEN);
		System.out.println("union: " + union(set1, set2));

		Set<Watercolors> subset = intersection(set1, set2);
		System.out.println("subset : " + subset);

		System.out.println("difference(set1, subset): " + difference(set1, subset));
		System.out.println("difference(set2, subset) :" + difference(set2, subset));

		System.out.println("complement(set1, set2): " + complement(set1, set2));

	}
}
