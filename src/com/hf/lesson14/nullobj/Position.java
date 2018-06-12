package com.hf.lesson14.nullobj;
/**
 * 职位
 * @author ciker
 * @desc   
 *
 */
public class Position {
	private String title;
	private Person person;
	public Position(String jobTitle,Person employee) {
		this.title=jobTitle;
		this.person=employee;
		if(person==null) {
			person=Person.NULL;
		}
	}
	public Position(String jobTitle) {
		this.title=jobTitle;
		person=Person.NULL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person newPerson) {
		person = newPerson;
		if(person==null) {
			person=Person.NULL;
		}
	}
	@Override
	public String toString() {
		return "Position [title=" + title + ", person=" + person + "]";
	}
	
	
}
