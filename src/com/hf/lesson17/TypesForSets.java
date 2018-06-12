package com.hf.lesson17;
// set和存储顺序

import java.util.Set;

public class TypesForSets {
	public static void main(String[] args) {
//		test(new HashSet<HashType>(), HashType.class);
//		test(new LinkedHashSet<HashType>(), HashType.class);
//		test(new TreeSet<TreeType>(), TreeType.class);
//		
//		test(new HashSet<SetType>(), SetType.class);// hashcode必须要实现
//		test(new HashSet<TreeType>(), TreeType.class);// hashcode必须要实现
//		test(new LinkedHashSet<SetType>(), SetType.class);
//		test(new LinkedHashSet<TreeType>(), TreeType.class);
//		test(new TreeSet<SetType>(), SetType.class);// 要实现Comparable接口有比较方法
		
		test(new SlowSet<TreeType>(), TreeType.class);
	}
	
	static <T> Set<T> fill(Set<T> set,Class<T> type){
		try {
			for(int i=0;i<10;i++) {
				set.add(type.getConstructor(int.class).newInstance(i));
			}
		}catch(Exception e) {throw new RuntimeException(e);}
		return set;
	}
	
	static <T> void test(Set<T> set, Class<T> type) {
		fill(set, type);
		fill(set, type);
		fill(set, type);
		System.out.println(set);
	}
}

class SetType{
	int i;
	public SetType(int i) {
		this.i=i;
	}
	public boolean equals(Object o) {
		return o instanceof SetType && (i == ((SetType)o).i);
	}
	
	public String toString() {
		return Integer.toString(i);
	}
//	public int hashCode() {
//		return i;
//	}
}

class HashType extends SetType{

	public HashType(int i) {
		super(i);
	}
	
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType>{

	public TreeType(int i) {
		super(i);
	}

	@Override
	public int compareTo(TreeType o) {
		// 前一个数大于后一个则先排，降序
		return (o.i < i ? -1 : (o.i == i ? 0 : 1));
	}
	
}
