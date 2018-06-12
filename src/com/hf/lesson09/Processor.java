package com.hf.lesson09;

public interface Processor {
	String name();
	Object process(Object input);
}
/**
 * 适配器模式
 * @author ciker
 * @desc   
 *
 */
class FilterAdapter implements Processor{
	Filter filter;
	public FilterAdapter(Filter filter) {
		this.filter=filter;
	}
	@Override
	public String name() {
		return filter.name();
	}

	@Override
	public Waveform process(Object input) {
		return filter.process((Waveform)input);
	}
	
}

class Filter{

	public String name() {
		return getClass().getSimpleName();
	}

	public Waveform process(Waveform input) {
		return input;
	}
	
}

interface t12 {
   int ONE = 1, TWO = 2;
}



