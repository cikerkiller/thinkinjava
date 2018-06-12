package com.hf.lesson12;

/**
 * 异常丢失
 * @author ciker
 * @desc   
 *
 */
public class LostMessage {
	void f() throws VeryImportantException{
		throw new VeryImportantException();
	}
	void despose() throws HohumException{
		throw new HohumException();
	}
	@SuppressWarnings("finally")
	public static void main(String[] args) {
		try {
			LostMessage lostMessage=new LostMessage();
			try {
				lostMessage.f();
			} finally {
				try {
					lostMessage.despose();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);//VeryImportantException不见了，
		}
		
		try {
			new LostMessage().f();
		} finally {
			return;
		}
		
		
	}
	
}
class VeryImportantException extends Exception{
	public String toString() {
		return "A very important exception";
	}
}
class HohumException extends Exception{
	public String toString() {
		return "A trivial exception";
	}
}

