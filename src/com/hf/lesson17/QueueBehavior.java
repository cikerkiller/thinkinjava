package com.hf.lesson17;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.hf.lesson15.Generator;

// 队列
public class QueueBehavior {
	private static int count = 10;
	static <T> void test(Queue<T> queue,Generator<T> gen) {
		for(int i=0;i<count;i++) {
			queue.offer(gen.next());
		}
		T t = queue.peek();
		while(t!=null) {
			System.out.print(queue.remove()+" ");
			t = queue.peek();
		}
		System.out.println();
	}
	
	static class Gen implements Generator<String> {
		String[] s = "one two three four five six seven eight nine ten".split(" ");
		int i;
		@Override
		public String next() {
			return s[i++];
		}
		
	}
	
	public static void main(String[] args) {
//		test(new PriorityQueue<String>(), new Gen());// 优先级队列
		test(new PriorityBlockingQueue<String>(), new Gen());// 优先级队列
//		test(new LinkedList<String>(), new Gen());// 按插入顺序,下面都是
		test(new ArrayBlockingQueue<String>(count), new Gen());
		test(new LinkedBlockingQueue<String>(), new Gen());
		test(new ConcurrentLinkedQueue<String>(), new Gen());
	}
}
