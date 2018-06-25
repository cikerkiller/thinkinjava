package com.hf.lesson21.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 生产者与消费者
// 餐厅
public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	BusBoy busBoy = new BusBoy(this);
	Chef chef = new Chef(this);
	boolean isClean = true;// 最初是干净的
	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
//		exec.execute(busBoy);
	}
	public static void main(String[] args) {
		new Restaurant();
	}
}

// 食物
class Meal {
	private final int orderNum;
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	public String toString() {
		return "Meal "+orderNum;
	}
}
// 服务员
class WaitPerson implements Runnable {
	private Restaurant restaurant;
	Lock lock =new ReentrantLock();
	Condition condition = lock.newCondition();
	public WaitPerson(Restaurant r) {
		this.restaurant = r;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				lock.lock();
				try {
					// 当食物还没做好的时候要等待
					while(restaurant.meal == null) {
						condition.await();
					}
				}finally{
					lock.unlock();
				}
				
				
				// 此处已经取得食物
				System.out.println("waitperson got "+restaurant.meal);
				restaurant.chef.lock.lock();
				try {
					// 此处食物已经被服务员拿走了，所有要唤醒厨师做菜
					restaurant.meal = null;
					restaurant.chef.condition.signalAll();// 必须拿到chef的锁,才能将其唤醒
				}finally{
					restaurant.chef.lock.unlock();
				}
			}
		}catch(InterruptedException e) {
			System.out.println("waitperson interrupt ");
		}
	}
	
}
// 厨师
class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;
	Lock lock =new ReentrantLock();
	Condition condition = lock.newCondition();
	public Chef(Restaurant r) {
		this.restaurant = r;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				lock.lock();
				try {
					// 食物做好后等待服务员拿走
					while(restaurant.meal != null) {
						condition.await();// 此处释放本身的锁
					}
				}finally{
					lock.unlock();
				}
				// 做好10份菜后完成工作
				if(++count == 10) {
					System.out.println("Out of food,closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up!");
				restaurant.waitPerson.lock.lock();
				try {
					// 此处食物做好了，等待服务员来拿走
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.condition.signalAll();// 唤醒服务员
				}finally{
					restaurant.waitPerson.lock.unlock();
				}
//				TimeUnit.MILLISECONDS.sleep(100);// 
			}
		}catch(InterruptedException e) {
			System.out.println("chef interrupt ");
		}
	}
}
// 练习26
class BusBoy implements Runnable{
	private Restaurant restaurant;
	public BusBoy(Restaurant restaurant) {
		this.restaurant=restaurant;
	}  
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					// 
					while(restaurant.isClean) {
						wait();
					}
				}
				System.out.println("isClean="+restaurant.isClean);
				restaurant.isClean = true;
			}
		}catch(InterruptedException e) {
			System.out.println("BusBoy interrupt ");
		}
		
	}
}
