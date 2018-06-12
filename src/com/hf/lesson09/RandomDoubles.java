package com.hf.lesson09;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class RandomDoubles {
	private static Random random=new Random(47);
	public double next() {return random.nextDouble();}
	public static void main(String[] args) {
//		RandomDoubles doubles=new RandomDoubles();
//		for(int i=0;i<7;i++) {
//			System.out.println(doubles.next()+" ");
//		}
		
		AdaptedRandomDoubles adaptedRandomDoubles=new AdaptedRandomDoubles(7);
		Scanner scanner=new Scanner(adaptedRandomDoubles);
		while(scanner.hasNext()) {
			System.out.println(scanner.nextDouble()+" ");
		}
	}
}
class AdaptedRandomDoubles extends RandomDoubles implements Readable{
	private int count;
	
	public AdaptedRandomDoubles(int count) {
		super();
		this.count = count;
	}
	
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count--==0) {
			return -1;
		}
		String result=Double.valueOf(next())+" ";
		cb.append(result);
		return result.length();
	}
	
}