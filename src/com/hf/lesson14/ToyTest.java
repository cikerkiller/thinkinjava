package com.hf.lesson14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ToyTest {
	static void printInfo(Class cc) {
		System.out.println("Class name: "+cc.getName()+" is interface? ["+cc.isInterface()+"]");
		System.out.println("Simple name: "+cc.getSimpleName());
		System.out.println("Canonical name: "+cc.getCanonicalName());
	}
	public static void main(String[] args) {
		Class c=null;
		try {
			c=Class.forName("com.hf.lesson14.FancyToy");
		} catch (ClassNotFoundException e) {
			System.out.println("Can`t find FancyToy");
			System.exit(1);
		}
		printInfo(c);

		for(Class face : c.getInterfaces()) {
			printInfo(face);
		}
		
		Class up = c.getSuperclass();
		Object obj = null;
		try {
			obj = up.newInstance();
			Class up1 =int.class;
			Class<Integer> type = Integer.TYPE;
			System.out.println(up1==type);// true
		} catch (InstantiationException e) {
			System.out.println("Can`t instantiate");
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.out.println("Can`t Access");
			System.exit(1);
		}
		printInfo(obj.getClass());
	}
}
class Toy extends ArrayList{
	Toy(){}
	Toy(int i){}
}
class FancyToy extends Toy implements List {
	FancyToy(){super(1);}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
