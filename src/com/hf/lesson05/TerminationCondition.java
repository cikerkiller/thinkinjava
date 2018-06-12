package com.hf.lesson05;

public class TerminationCondition {
	public static void main(String[] args) {
		Book book=new Book(true);
		book.checkIn();//每一本书都要签入
		new Book(true);//未签入，checkedOut=true,所以Error : checked out
		System.gc();
	}
}
class Book{
	boolean checkedOut=false;
	Book(boolean checkedOut){
		this.checkedOut=checkedOut;
	}
	
	void checkIn() {
		this.checkedOut=false;
	}

	@Override
	protected void finalize() throws Throwable {
		if(checkedOut) {
			System.out.println("Error : checked out");
		}
		super.finalize();
	}
	
}