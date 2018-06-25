package com.hf.lesson21.simulation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarBuilder {
	public static void main(String[] args) throws Exception{
		CarQueue chassisQueue = new CarQueue();
		CarQueue finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		RobotPool robotPool = new RobotPool();
		exec.execute(new EngineRobot(robotPool));
		exec.execute(new DriveTrainRobot(robotPool));
		exec.execute(new WheelRobot(robotPool));
		exec.execute(new Assembler(chassisQueue,finishingQueue,robotPool));
		exec.execute(new Reporter(finishingQueue));
		exec.execute(new ChassisBuilder(chassisQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
class Car {
	private final int id;
	private boolean engine = false,  driveTrain = false, wheels = false;
	public Car(int idn) {
		this.id = idn;
	}
	public Car() {
		this.id = -1;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void addEngine() {
		engine = true;
	}
	public synchronized  void addDriveTrain() {
		driveTrain = true;
	}
	public synchronized void addWheels() {
		wheels = true;
	}
	public synchronized String toString() {
		return "Car "+id+" [ engine: "+engine+" driveTrain: "+driveTrain+" wheels: "+wheels+" ]";
	}
}

class CarQueue extends LinkedBlockingQueue<Car>{}

class ChassisBuilder implements Runnable {
	private CarQueue carQueue;
	private int counter = 0;
	public ChassisBuilder(CarQueue carQueue) {
		super();
		this.carQueue = carQueue;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				Car c = new Car(counter++);
				System.out.println("ChassisBuilder created "+c);
				carQueue.put(c);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: ChassisBuilder");
		}
		System.out.println("ChassisBuilder off");
	}
}

class Assembler implements Runnable {
	private CarQueue chassisQueue, finishingQueue;
	private Car car;
	private CyclicBarrier barrier  = new CyclicBarrier(4);
	private RobotPool robotPool;
	public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
		super();
		this.chassisQueue = chassisQueue;
		this.finishingQueue = finishingQueue;
		this.robotPool = robotPool;
	}
	public Car car() {
		return car;
	}
	public CyclicBarrier barrier() {
		return barrier;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				car = chassisQueue.take();
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WheelRobot.class, this);
				barrier.await();
				finishingQueue.put(car);
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting Assembler via interrupt");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Assembler off");
	}
}

class Reporter implements Runnable {
	private CarQueue carQueue;

	public Reporter(CarQueue carQueue) {
		super();
		this.carQueue = carQueue;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println(carQueue.take());
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting reporter via interrupt");
		}
		System.out.println("Reporter off");
	}
}

abstract class Robot implements Runnable {
	private RobotPool pool;

	public Robot(RobotPool pool) {
		super();
		this.pool = pool;
	}
	protected Assembler assembler;
	public Robot assignAssembler(Assembler assembler) {
		this.assembler = assembler;
		return this;
	}
	private boolean engage = false;
	public synchronized void engage() {
		engage = true;
		notifyAll();
	}
	
	abstract protected void performService();
	public void run() {
		try {
			powerDown();
			while(!Thread.interrupted()) {
				performService();
				assembler.barrier().await();
				powerDown();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting "+this +" via interrupt ");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		System.out.println(this+" off");
	}
	
	private synchronized void powerDown() throws InterruptedException {
		engage = false;
		assembler = null;
		pool.release(this);
		while(engage == false) {
			wait();
		}
	}
	public String toString() {
		return getClass().getName();
	}
}

class EngineRobot extends Robot {
	public EngineRobot(RobotPool pool) {
		super(pool);
	}
	@Override
	protected void performService() {
		System.out.println(this+" installing engine");
		assembler.car().addEngine();
	}
}

class DriveTrainRobot extends Robot {
	public DriveTrainRobot(RobotPool pool) {
		super(pool);
	}
	@Override
	protected void performService() {
		System.out.println(this+" installing DriveTrain");
		assembler.car().addDriveTrain();
	}
}

class WheelRobot extends Robot {
	public WheelRobot(RobotPool pool) {
		super(pool);
	}
	@Override
	protected void performService() {
		System.out.println(this+" installing Wheels");
		assembler.car().addWheels();
	}
}

class RobotPool {
	private Set<Robot> pool = new HashSet<>();
	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}
	// 雇佣
	public synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException{
		for(Robot  robot : pool) {
			if(robot.getClass().equals(robotType)) {
				pool.remove(robot);
				robot.assignAssembler(d);
				robot.engage();
				return;
			}
		}
		wait();
		hire(robotType, d);
	}
	// 完成工作后放入池中
	public synchronized void release(Robot r) {
		add(r);
	}
}
