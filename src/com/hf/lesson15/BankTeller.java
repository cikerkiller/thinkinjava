package com.hf.lesson15;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * 内部类
 * 
 * @author ciker
 * @desc
 *
 */
public class BankTeller {
	public static void main(String[] args) {
		Random rand = new Random(47);
		Queue<Customer> line = new LinkedList<>();
		Generators.fill(line, Customer.generator(), 15);
		for (Customer c : line) {
			System.out.println(c);
		}

		List<Teller> tellers = new ArrayList<>();
		Generators.fill(tellers, Teller.generator(), 4);
		for (Customer c : line) {
			serve(tellers.get(rand.nextInt(tellers.size())), c);
		}

	}

	public static void serve(Teller t, Customer c) {
		System.out.println(t + " serves " + c);
	}
}

class Customer {
	private static long counter = 1;
	private final long id = counter++;

	public Customer() {
	}

	public String toString() {
		return "Customer " + id;
	}

	public static Generator<Customer> generator() {
		return new Generator<Customer>() {

			@Override
			public Customer next() {
				return new Customer();
			}
		};
	}
}

class Teller {
	private static long counter = 1;
	private final long id = counter++;

	public Teller() {
	}

	public String toString() {
		return "Teller " + id;
	}

	public static Generator<Teller> generator() {
		return new Generator<Teller>() {

			@Override
			public Teller next() {
				return new Teller();
			}
		};
	}
}