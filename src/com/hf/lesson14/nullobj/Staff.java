package com.hf.lesson14.nullobj;

import java.util.ArrayList;

public class Staff extends ArrayList<Position>{
	public void add(String title,Person person) {
		add(new Position(title, person));
	}
	public void add(String...titles) {
		for(String title : titles) {
			add(new Position(title));
		}
	}
	public Staff(String...titles) {
		add(titles);
	}
	/**
	 * 检测职位是否可用
	 * @param title
	 * @return
	 */
	public boolean positionAvailable(String title) {
		for(Position position : this) {
			// 有这个职位并且还没有人占用
			if(position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 填充这个职位
	 * @param title
	 * @param hire
	 */
	public void fillPosition(String title , Person hire) {
		for(Position position : this) {
			if(position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
				position.setPerson(hire);
				return ;
			}
		}
		throw new RuntimeException("Position "+title+" not available");
	}
	
	public static void main(String[] args) {
		Staff staff = new Staff("President", "CTO", "Marketing Manager");
		staff.fillPosition("President", new Person("Me", "Last", "The Top, lonely At"));
		staff.fillPosition("Marketing Manager", new Person("Janet", "Planner", "The Burbs"));
		
		if(staff.positionAvailable("SoftWare Engineer")) {
			staff.fillPosition("SoftWare Engineer", new Person("Bob", "coder", "Bright Lighgt City"));
		}
		staff.add("SoftWare");
		if(staff.positionAvailable("SoftWare")) {
			staff.fillPosition("SoftWare", new Person("Bob", "coder", "Bright Lighgt City"));
		}
		System.out.println(staff);
		
	}
}
