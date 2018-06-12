package com.hf.lesson15;

import java.awt.Color;

/**
 * 边界
 * 
 * @author ciker
 * @desc
 *
 */
public class BasicBounds {
	public static void main(String[] args) {
		Solid<Bouned> solid = new Solid<Bouned>(new Bouned());
		solid.getColor();
		System.out.println(solid.getY());
		solid.weight();

	}
}

interface HasColor {
	Color getColor();
}

class Colored<T extends HasColor> {
	T item;

	Colored(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}

	Color getColor() {
		return item.getColor();
	}
}

class Dimension {
	public int x, y, z;
}

class ColoredDimension<T extends Dimension & HasColor> {
	T item;

	ColoredDimension(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}

	Color getColor() {
		return item.getColor();
	}

	int getX() {
		return item.x;
	}

	int getY() {
		return item.y;
	}

	int getZ() {
		return item.z;
	}
}

interface Weright {
	int weight();
}

class Solid<T extends Dimension & HasColor & Weright> {
	T item;

	Solid(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}

	Color getColor() {
		return item.getColor();
	}

	int getX() {
		return item.x;
	}

	int getY() {
		return item.y;
	}

	int getZ() {
		return item.z;
	}

	int weight() {
		return item.weight();
	}
}

class Bouned extends Dimension implements HasColor, Weright {

	@Override
	public int weight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
