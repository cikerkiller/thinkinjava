package com.hf.lesson18;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDome {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = buf.asIntBuffer();
		ib.put(new int[] {11,42,47,99,143,811,1016});
		System.out.println(ib.get(2));
		ib.put(2, 55);
		System.out.println(ib.get(2));
		ib.flip();
		while(ib.hasRemaining()) {
			System.out.println(ib.get());
		}
		
//		try {
//			FileChannel fc = new FileOutputStream("C:\\\\Users\\\\WIN10\\\\Desktop\\\\2.txt").getChannel();
//			fc.write(buf);
//			fc.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
