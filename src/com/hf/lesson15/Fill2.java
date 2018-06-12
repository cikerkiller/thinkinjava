package com.hf.lesson15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 适配器仿真潜在类型机制
 * @author ciker
 * @desc   
 *
 */
public class Fill2 {
	/**
	 * 填充数据
	 * @param addable
	 * @param type
	 * @param size
	 */
	public static <T> void fill(Addable<T> addable,Class<? extends T> type,int size) {
		try{
			for(int i=0;i<size;i++) {
				addable.add(type.newInstance());
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static <T> void fill(Addable<T> addable,Generator<T> gen,int size) {
		for(int i=0;i<size;i++) {
			addable.add(gen.next());
		}
	}
	
	public static void main(String[] args) {
		List<Coffee> coffees = new ArrayList<>();
		fill(new AddableCollectionAdapter<Coffee>(coffees), Coffee.class, 5);
//		fill(Adapter.collectionAdapter(coffees), Coffee.class, 5);
//		for(Coffee c : coffees) {
//			System.out.println(c);
//		}
		AddableSimpleQueue<Mocha> addableSimpleQueue=new AddableSimpleQueue<Mocha>();
		fill(addableSimpleQueue, Mocha.class, 5);
		for(Mocha c : addableSimpleQueue) {
			System.out.println(c);
		}
	}
	
	
}
interface Addable<T>{
	void add(T t);
}

/**
 * 集合装配器
 * @author ciker
 * @desc   
 *
 */
class AddableCollectionAdapter<T> implements Addable<T> {
	private Collection<T> col;
	
	public AddableCollectionAdapter(Collection<T> col) {
		this.col=col;
	}
	@Override
	public void add(T t) {
		col.add(t);
	}
}

class Adapter {
	public static <T> Addable<T> collectionAdapter(Collection<T> c){
		return new AddableCollectionAdapter<T>(c);
	}
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T>{

	@Override
	public void add(T item) {
		super.add(item);
	}
	
}
