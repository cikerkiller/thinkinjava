package com.hf.lesson15;

import java.util.ArrayList;
import java.util.Random;

/**
 * 商店
 * 
 * @author ciker
 * @desc
 *
 */
public class Store extends ArrayList<Aisle> {
	private ArrayList<CheckoutStand> checkouts = new ArrayList<>();
	private Office office = new Office();

	public Store(int nAisle, int nShelves, int nProducts) {
		for (int i = 0; i < nAisle; i++) {
			add(new Aisle(nShelves, nProducts));
		}
	}

	@Override
	public String toString() {
		StringBuffer sbu = new StringBuffer();
		for (Aisle a : this) {
			for (Shelf s : a) {
				for (Product p : s) {
					sbu.append(p).append("\n");
				}
			}
		}
		return sbu.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Store(14, 5, 10));
	}

}

/**
 * 产品
 * 
 * @author ciker
 * @desc
 *
 */
class Product {
	private final int id;
	private String description;
	private double price;

	public Product(int id, String description, double price) {
		this.id = id;
		this.description = description;
		this.price = price;
		System.out.println(toString());
	}

	public String toString() {
		return id + " : " + description + ", price: $" + price;
	}

	public void priceChange(double change) {
		price += change;
	}

	public static Generator<Product> generator = new Generator<Product>() {
		private Random rand = new Random(47);

		@Override
		public Product next() {
			return new Product(rand.nextInt(1000), "Test", Math.round(rand.nextDouble() * 1000.0) + 0.99);
		}
	};

}

/**
 * 货架
 * 
 * @author ciker
 * @desc
 *
 */
class Shelf extends ArrayList<Product> {
	public Shelf(int nProducts) {
		Generators.fill(this, Product.generator, nProducts);
	}
}

/**
 * 走廊
 * 
 * @author ciker
 * @desc
 *
 */
class Aisle extends ArrayList<Shelf> {
	public Aisle(int nShelvs, int nProducts) {
		for (int i = 0; i < nShelvs; i++) {
			add(new Shelf(nProducts));
		}
	}
}

class CheckoutStand {
}

class Office {
}
