package com.hf.lesson20;

import com.hf.lesson20.test.Test;

public class Testable {
	public void execute() {
		System.out.println("Executing...");
	}
	
	@Test void testExecute() {
		execute();
	}
}
