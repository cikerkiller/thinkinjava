package com.hf.lesson10.event;

/**
 * 温室控制系统
 * @author ciker
 * @desc   
 *
 */
public class GreenhouseControls extends Controller {
	private boolean light = false;
	public class LightOn extends Event{

		public LightOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			light = true;
		}

		@Override
		public String toString() {
			return "Light is on";
		}
	}
	
	public class LightOff extends Event{
		
		public LightOff(long delayTime) {
			super(delayTime);
		}
		
		@Override
		public void action() {
			light = false;
		}
		
		@Override
		public String toString() {
			return "Light is off";
		}
	}
	
	private boolean water = false;
	
	public class WaterOn extends Event{

		public WaterOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = true;
		}
		@Override
		public String toString() {
			return "Greenhouse water is on";
		}
	}
	public class WaterOff extends Event{
		
		public WaterOff(long delayTime) {
			super(delayTime);
		}
		
		@Override
		public void action() {
			water = false;
		}
		@Override
		public String toString() {
			return "Greenhouse water is off";
		}
	}
	
	public class Bell extends Event{

		public Bell(long delayTime) {
			super(delayTime);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void action() {
			addEvent(new Bell(delayTime));
		}
		@Override
		public String toString() {
			return "Bing!";
		}
	}
	
	
	public class Restart extends Event{
		private Event[] eventList;
		public Restart(long delayTime,Event[] eventList) {
			super(delayTime);
			this.eventList=eventList;
			for(Event e:eventList) {
				addEvent(e);
			}
		}
		
		@Override
		public void action() {
			// 重启
			for(Event e:eventList) {
				e.start();// return each event
				addEvent(e);
			}
			start();
			addEvent(this);// return this event
		}
		@Override
		public String toString() {
			return "Restarting system";
		}
	}
	
	public static class Terminate extends Event{

		public Terminate(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}
		@Override
		public String toString() {
			return "Terminating";
		}
	}
	
	
}
