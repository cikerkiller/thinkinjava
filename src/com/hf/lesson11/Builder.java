package com.hf.lesson11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 生成器，填充数组、ArrayList、...  
 * @author ciker
 * @desc   
 *
 */
public class Builder {
	private Collection<Object> collection;
	private Iterator<Object> iterator;
	public Builder(Collection<Object> collection) {
		this.collection=collection;
		iterator=collection.iterator();
	}
	public boolean hasNext() {
		return iterator.hasNext();
	}
	public Object next() {
		return iterator.next();
	}
	public <T> void add(Collection<Object> collection,@SuppressWarnings("unchecked") T...t) {
		for(T tt:t) {
			collection.add(tt);
		}
		this.collection.addAll(collection);
		iterator=this.collection.iterator();
	}
	
	@Override
	public String toString() {
		return collection.toString();
	}
	public static void main(String[] args) {
		String[] moreStr= {"this","sas","dwa"};
		Collection<Object> con=new ArrayList<>();
		Collections.addAll(con, moreStr);
		Builder builder=new Builder(con);
		System.out.println(builder);
		builder.add(new ArrayList<>(), "afg","sd","gfa");
		System.out.println(builder);
		builder.add(new HashSet<>());
		while(builder.hasNext()) {
			System.out.println(builder.next());
		}
	}
}
