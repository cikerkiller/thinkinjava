package com.hf.lesson21.simulation;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 银行出纳员仿真
public class BankTellerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
		exec.execute(new CustomerGenerator(customers));
		exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

// 顾客
class BankCustomer {
	private final int serviceTime;
	public BankCustomer(int tm) {
		this.serviceTime = tm;
	}
	public int  getServiceTime() {
		return serviceTime;
	}
	public String toString() {
		return "["+serviceTime+"]";
	}
}

class CustomerLine extends ArrayBlockingQueue<BankCustomer> {
	public CustomerLine(int capacity) {
		super(capacity);
	}
	public String toString() {
		if(this.size() == 0) {
			return "[Empty]";
		}
		StringBuffer sbu = new StringBuffer();
		for(BankCustomer customer : this) {
			sbu.append(customer);
		}
		return sbu.toString();
	}
}

// 顾客仿真,随机生成顾客
class CustomerGenerator implements Runnable {
	private CustomerLine customers;
	private static Random rand = new Random(47);
	
	public CustomerGenerator(CustomerLine customers) {
		this.customers = customers;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new BankCustomer(rand.nextInt(1000)));
			}
		} catch (InterruptedException e) {
			System.out.println("CustomerGenerator interrupted");
		}
		System.out.println("CustomerGenerator terminating");
	}
}

// 出纳员
class Teller implements Runnable, Comparable<Teller> {
	private static int counter = 0;
	private final int id = counter++;
	private int customersServed = 0;// 出纳员服务的顾客个数
	private CustomerLine customers;
	private boolean servingCustomerLine = true;
	public Teller(CustomerLine cq) {
		this.customers = cq;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				BankCustomer customer = customers.take();// 从队列中服务一名顾客
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());// 出纳员为当前顾客的服务时间
				synchronized (this) {
					customersServed++;
					while(!servingCustomerLine) {
						wait();// 没有顾客时等待
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println(this+" interrupted");
		}
		System.out.println(this+" terminating");
	}
	
	public synchronized void doSomethingElse() {
		customersServed = 0;
		servingCustomerLine = false;
	}
	public synchronized void serveCustomerLine() {
		assert !servingCustomerLine : "already serving: "+this;
		servingCustomerLine = true;
		notifyAll();
	}
	
	public String toString() {
		return "Teller " + id + " ";
	}
	public String shortString() {
		return "T"+id;
	}
	public synchronized int compareTo(Teller other) {
		return customersServed < other.customersServed ? -1 : (customersServed == other.customersServed ? 0 : 1);
	}
}

class TellerManager implements Runnable {
	private ExecutorService exec;
	private CustomerLine customers;
	private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
	private Queue<Teller>  tellerDoingOtherThings = new LinkedList<>();
	private int adjustmentPeriod;
	private static Random rand = new Random(47);
	public TellerManager(ExecutorService ex, CustomerLine customers, int adjustmentPeriod) {
		this.exec = ex;
		this.customers = customers;
		this.adjustmentPeriod = adjustmentPeriod;
		
		Teller teller = new Teller(customers);
		exec.execute(teller);
		workingTellers.add(teller);
	}
	
	// 控制系统
	public void adjustTellerNumber() {
		// 顾客数超过了正在工作的出纳员两倍时
		if(customers.size() / workingTellers.size() >2) {
			if (tellerDoingOtherThings.size() > 0) {
				// 从闲着的出纳员队列中取出一个来招待顾客
				Teller teller = tellerDoingOtherThings.remove();
				teller.serveCustomerLine();
				workingTellers.offer(teller);
				return;
			}else{
				// 么有闲着的出纳员则新增出纳员
				Teller telelr = new Teller(customers);
				exec.execute(telelr);
				workingTellers.add(telelr);
				return;
			}
		}
		
		if(workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
			reassignOneTeller();
		}
		
		if(customers.size() == 0) {
			while(workingTellers.size() > 1) {
				reassignOneTeller();
			}
		}
	}
	
	private void reassignOneTeller() {
		Teller teller = workingTellers.poll();
		teller.doSomethingElse();
		tellerDoingOtherThings.offer(teller);// 将工作完后的出纳员添加进做其他事的队列中
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(customers+" {");
				for(Teller teller : workingTellers) {
					System.out.print(teller.shortString() + " ");
				}
				System.out.println("}");
			}
		} catch (InterruptedException e) {
			System.out.println(this+" interrupted");
		}
		System.out.println(this+" terminating");
	}
	
	public String toString() {
		return "TellerManager";
	}
	
	
}