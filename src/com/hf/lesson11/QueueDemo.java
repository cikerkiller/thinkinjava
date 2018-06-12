package com.hf.lesson11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 队列：FIFO 先进先出 
 * @author ciker
 * @desc   
 *
 */
public class QueueDemo {
	public static void printQ(Queue q) {
		while(q.peek()!=null) {
			System.out.print(q.remove()+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue=new LinkedList<>();
		Random random=new Random(47);
		for(int i=0;i<10;i++) {
			queue.offer(random.nextInt(i+10));
		}
		printQ(queue);
		
		Queue<Character> queue2=new LinkedList<>();
		for(char c : "this is a dog".toCharArray()) {
			queue2.offer(c);
		}
		printQ(queue2);
		
	}
}
