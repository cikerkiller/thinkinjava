package com.hf.lesson21.performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReaderWriterList<T> {
	private ArrayList<T> lockedList;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	
	public ReaderWriterList(int size, T initialValue) {
		lockedList = new ArrayList<>(Collections.nCopies(size, initialValue));
	}
	public T set(int index, T element) {
		Lock wlock = lock.writeLock();
		wlock.lock();
		try {
			return lockedList.set(index, element);
		}finally {
			wlock.unlock();
		}
	}
	
	public T get(int index) {
		ReadLock rlock = lock.readLock();
		rlock.lock();
		try {
			if(lock.getReadLockCount()> 1) {
				// 多个读取者获取到读锁
				System.out.println(lock.getReadLockCount());
			}
			return lockedList.get(index);
		}finally {
			rlock.unlock();
		}
	}
	
	public static void main(String[] args) {
		new ReaderWriterListTest(30, 0);
	}
}
class ReaderWriterListTest {
	ExecutorService exec = Executors.newCachedThreadPool();
	private static final int SIZE = 100;
	private static Random rand = new Random(47);
	private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE, 0);
	private class Writer implements Runnable {
		public void run() {
			try {
				for(int i=0;i<20;i++) {
					list.set(i, rand.nextInt());
					TimeUnit.MILLISECONDS.sleep(100);
				}
			} catch (InterruptedException e) {
			}
			System.out.println("Writer finished, shutting down ");
			exec.shutdownNow();
		}
	}
	
	private class Reader implements  Runnable {
		public void run() {
			while(!Thread.interrupted()) {
				try {
					for(int i=0;i<SIZE;i++) {
						list.get(i);
						TimeUnit.MILLISECONDS.sleep(1);
					}
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	public ReaderWriterListTest(int readers, int writer) {
		for(int i=0;i<readers;i++) {
			exec.execute(new Reader());
		}
		for(int i=0;i<writer;i++) {
			exec.execute(new Writer());
		}
	}
	
	
}