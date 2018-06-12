package com.hf.lesson15;

public class BaseGenerator<T> implements Generator<T> {
	private Class<T> type;
	public BaseGenerator(Class<T> type) {
		this.type=type;
	}
	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> Generator<T> create(Class<T> type){
		return new BaseGenerator<T>(type);
	}

}
