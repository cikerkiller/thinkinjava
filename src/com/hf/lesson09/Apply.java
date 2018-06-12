package com.hf.lesson09;

import java.util.Arrays;

/**
 * 策略设计模式
 * @author ciker
 * @desc   
 *
 */
public class Apply {
	public static void process(Processor p,Object s) {
		System.out.println("using processor "+p.name());
		System.out.println(p.process(s));
	}
	
	public static String s="this is a test";
	public static void main(String[] args) {
		process(new Upcase(), s);
		process(new Downcase(), s);
		process(new Splitter(), s);
	}
	
}

class Upcase implements Processor{
	public String process(Object input) {
		return ((String)input).toUpperCase();
	}

	@Override
	public String name() {
		
		return getClass().getSimpleName();
	}
}

class Downcase implements Processor{
	public String process(Object input) {
		return ((String)input).toLowerCase();
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}
}
class Splitter implements Processor{
	public String process(Object input) {
		return Arrays.toString(((String)input).split(" "));
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}
}
