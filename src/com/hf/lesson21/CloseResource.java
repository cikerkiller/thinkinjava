package com.hf.lesson21;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8000);
		InputStream socketInput = new Socket("localhost",8000).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("shutdown all thread");
		exec.shutdownNow();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing: "+socketInput.getClass().getName());
		socketInput.close();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing: "+System.in.getClass().getName());
		System.in.close();
		System.exit(0);
	}
}
