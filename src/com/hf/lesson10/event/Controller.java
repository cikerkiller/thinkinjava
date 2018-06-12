package com.hf.lesson10.event;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	private List<Event> eventList=new ArrayList<>();
	public void addEvent(Event e) {eventList.add(e);}
	public void run() {
		while(eventList.size()>0) {
			for(Event e:new ArrayList<>(eventList)){
				if(e.ready()) {
					System.out.println(e);
					e.action();// 在目前的设计中，不知道Event到底做了什么：“使变化的事物与不变的事物相互分离”
					eventList.remove(e);
				}
			}
		}
	}
}
