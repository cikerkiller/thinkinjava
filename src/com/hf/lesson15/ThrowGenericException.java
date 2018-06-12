package com.hf.lesson15;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数化异常
 * 
 * @author ciker
 * @desc
 *
 */
public class ThrowGenericException {
	public static void main(String[] args) {
		// 相当于list
		ProcessRunner<String, Failure1> runner = new ProcessRunner<>();
		for (int i = 0; i < 3; i++) {
			runner.add(new Processor1());
		}

		try {
			System.out.println(runner.processAll());
		} catch (Failure1 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProcessRunner<Integer, Failure2> runner2 = new ProcessRunner<>();
		for (int i = 0; i < 3; i++) {
			runner2.add(new Processor2());
		}
		try {
			System.out.println(runner2.processAll());
		} catch (Failure2 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

interface Processor<T, E extends Exception> {
	void process(List<T> resultCollector) throws E;
}

class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {

	// 返回有数据的list
	List<T> processAll() throws E {
		List<T> resultCollector = new ArrayList<>();
		// 主方法里增加了三个Processor1,在此处遍历
		for (Processor<T, E> p : this) {
			// 这个方法给list添加数据
			p.process(resultCollector);
		}
		return resultCollector;
	}
}

class Failure1 extends Exception {
}

class Processor1 implements Processor<String, Failure1> {
	static int count = 3;

	@Override
	public void process(List<String> resultCollector) throws Failure1 {
		if (count-- > 1) {
			resultCollector.add("help!");
		} else {
			resultCollector.add("Ho");
		}

		if (count < 0) {
			throw new Failure1();
		}

	}

}

class Failure2 extends Exception {
}

class Processor2 implements Processor<Integer, Failure2> {
	static int count = 2;

	@Override
	public void process(List<Integer> resultCollector) throws Failure2 {
		if (count-- == 0) {
			resultCollector.add(47);
		} else {
			resultCollector.add(11);
		}
		if (count < 0) {
			throw new Failure2();
		}
	}

}