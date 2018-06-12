package com.hf.lesson11;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * 优先级队列: 依赖于具体实现，算法的选择最重要
 * @author ciker
 * @desc   
 *
 */
public class PriorityQueueDemo {
	public static void main(String[] args) {
		PriorityQueue<Integer> queue=new PriorityQueue<>();
		Random ran=new Random(47);
		for(int i=0;i<10;i++) {
			queue.offer(ran.nextInt(i+10));
		}
		QueueDemo.printQ(queue);
		
		List<Integer> ints = Arrays.asList(25,55,25,14,21,32,14,21,22,22,58,132,1);
		queue=new PriorityQueue<>(ints);
		QueueDemo.printQ(queue);
		
		queue=new PriorityQueue<>(ints.size(), Collections.reverseOrder());
		queue.addAll(ints);
		QueueDemo.printQ(queue);
		
		String fact = "this is a test";
		List<String> strs = Arrays.asList(fact.split(""));
		PriorityQueue<String> stringQ=new PriorityQueue<>(strs);
		QueueDemo.printQ(stringQ);
		
		stringQ=new PriorityQueue<>(strs.size(),Collections.reverseOrder());// 反序
		stringQ.addAll(strs);
		QueueDemo.printQ(stringQ);
		
		Set<Character> characters=new HashSet<>();
		for(char c:fact.toCharArray()) {
			characters.add(c);
		}
		PriorityQueue<Character> queue2=new PriorityQueue<>(characters);
		QueueDemo.printQ(queue2);
		PriorityQueue<Test22> priorityQueue=new PriorityQueue<>();
		for(int i=0;i<5;i++) {
			priorityQueue.offer(new Test22());//java.lang.ClassCastException: com.hf.lesson11.Test22 cannot be cast to java.lang.Comparable
		}
		QueueDemo.printQ(priorityQueue);
		
		
	}
}

class Test22{}
