package com.hf.lesson21.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.hf.lesson19.Course;
import com.hf.lesson19.Food;

//饭店仿真
public class RestaurantWithQueues {
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		Restaurant restaurant = new Restaurant(exec, 5, 2);
		exec.execute(restaurant);
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

// 订单
class Order {
	private static int counter = 0;
	private final int id = counter++;
	private final Customer customer;
	private final WaitPerson waitPerson;
	private final Food food;
	public Order(Customer customer, WaitPerson waitPerson, Food food) {
		this.customer = customer;
		this.waitPerson = waitPerson;
		this.food = food;
	}
	public Customer getCustomer() {
		return customer;
	}
	public WaitPerson getWaitPerson() {
		return waitPerson;
	}
	public Food item() {
		return food;
	}
	public String toString() {
		return "Order: "+id+" item: "+food+" for: "+customer +" served by: "+waitPerson;
	}
}

// 盘子
class Plate {
	private final Order order;
	private final Food food;
	public Plate(Order order, Food food) {
		super();
		this.order = order;
		this.food = food;
	}
	public Order getOrder() {
		return order;
	}
	public Food getFood() {
		return food;
	}
	@Override
	public String toString() {
		return food.toString();
	}
}

// 顾客
class Customer implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final WaitPerson waitPerson;
	// 内部没有容量的阻塞队列，因此每个put()必须等待一个take()
	private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();
	public Customer(WaitPerson waitPerson) {
		super();
		this.waitPerson = waitPerson;
	}
	public void deliver(Plate p) throws InterruptedException {
		placeSetting.put(p);
	}
	@Override
	public void run() {
		for(Course course : Course.values()) {
			Food food = course.randomSelection();
			try {
				waitPerson.placeOrder(this, food);
				System.out.println(this + "eating "+placeSetting.take());
			} catch (InterruptedException e) {
				System.out.println(this+"watting for "+course +" interrupted");
				break;
			}
		}
		System.out.println(this + "finished meal, leaving");
	}
	public String toString() {
		return "Customer "+id+" ";
	}
}
// 服务员
class WaitPerson implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();
	public WaitPerson(Restaurant restaurant) {
		this.restaurant=restaurant;
	}
	public void placeOrder(Customer cust, Food food) {
		try {
			restaurant.orders.put(new Order(cust, this, food));
		} catch (InterruptedException e) {
			System.out.println(this + " placeOrder interrupted");
		}
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Plate plate = filledOrders.take();
				System.out.println(this + "received "+plate + " delivering to "+plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this+"off duty");
	}
	public String toString() {
		return "WaitPerson " + id + " ";
	}
}
// 厨师
class Chef implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	private static Random rand = new Random(47);
	public Chef(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Order order = restaurant.orders.take();
				Food item = order.item();
				
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				
				Plate  plate = new Plate(order, item);
				order.getWaitPerson().filledOrders.put(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}
	public String toString() {
		return "Chef "+id+" ";
	}
}
// 餐厅
class  Restaurant implements Runnable {
	private List<WaitPerson> waitPersons = new ArrayList<>();
	private List<Chef> chefs = new ArrayList<>();
	private ExecutorService exec;
	private static Random rand = new Random(47);
	BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
	public Restaurant(ExecutorService exec, int nWaitPersons, int nChefs) {
		this.exec = exec;
		for(int i=0;i<nWaitPersons;i++) {
			WaitPerson waitPerson = new WaitPerson(this);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
		}
		
		for(int i=0;i<nChefs;i++) {
			Chef chef = new Chef(this);
			chefs.add(chef);
			exec.execute(chef);
		}
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
				
				Customer c = new Customer(wp);
				exec.execute(c);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Restaurant interrupted");
		}
		System.out.println("restaurant closing");
	}
}