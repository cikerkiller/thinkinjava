package com.hf.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	public WaitPerson(Restaurant r) {
		this.restaurant = r;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// 当前服务员本身是共享资源
				synchronized (this) {
					// 当食物还没做好的时候要等待
					while(restaurant.meal == null) {
						wait();// 此处释放本身的锁
					}
				}
				// 此处已经取得食物
				System.out.println("waitperson got "+restaurant.meal);
				synchronized (restaurant.chef) {
					// 此处食物已经被服务员拿走了，所有要唤醒厨师做菜
					restaurant.meal = null;
					restaurant.chef.notifyAll();// 必须拿到chef的锁,才能将其唤醒
				}
//				synchronized (restaurant.busBoy) {
//					System.out.println("此处是脏的");
//					restaurant.isClean = false;
//					restaurant.busBoy.notifyAll();
//				}
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
	public Chef(Restaurant r) {
		this.restaurant = r;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					// 食物做好后等待服务员拿走
					while(restaurant.meal !=null) {
						wait();
					}
				}
				// 做好10份菜后完成工作
				if(++count == 10) {
					System.out.println("Out of food,closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up!");// 当走到这的时候，waitperson在wait()中，所有中断操作直接会抛出异常
				synchronized (restaurant.waitPerson) {
					// 此处食物做好了，等待服务员来拿走
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();// 唤醒服务员
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
