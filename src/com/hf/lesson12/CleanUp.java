package com.hf.lesson12;

public class CleanUp {
	public static void main(String[] args) {
		try {
			System.out.println(CleanUp.class.getClassLoader().getResource(".").getPath());
			InputFile in = new InputFile("./CleanUp.java");
			try {
				String s;
				int i=1;
				while((s=in.getLine())!=null) 
					;
			} catch (Exception e) {
				System.out.println("Caught Exception in main");
				e.printStackTrace(System.out);
			}finally {
				in.dispose();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("inputfile construction failed");
		}
		
	}
}
