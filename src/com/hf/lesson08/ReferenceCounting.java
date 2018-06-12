package com.hf.lesson08;

import java.util.concurrent.TimeUnit;

public class ReferenceCounting {
	Share share=new Share();
	
	
	public static void main(String[] args) {
		Share share=new ReferenceCounting().share;
		Composing[] composings= {new Composing(share),new Composing(share),new Composing(share),new Composing(share),};
		for(Composing c:composings) {
			c.dispose();
		}
		new ReferenceCounting();
//		System.runFinalization();
		try {
			TimeUnit.MICROSECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.gc();
	
	}

	@Override
	protected void finalize() throws Throwable {
		share.finalize();
		System.out.println("finalize..");
	}
	
}
class Share{
	private int refcount=0;
	private static long counter=0;
	private final long id =counter++;
	public Share() {
		System.out.println("Creating "+this);
	}
	public void addRef() {
		refcount++;
	}
	protected  void dispose() {
		// 当share被引用的次数为0时就可以销毁了
		if(--refcount==0) {
			System.out.println("Dispose..."+this);
		}
	}
	public String toString() {return "Share "+id;}
	@Override
	protected void finalize() throws Throwable {
		if(refcount!=0) {
			System.out.println("sasf");
    	}else {
			super.finalize();
		}
		
	}
	
	
}

class Composing{
	private Share share;
	private static long counter=0;
	private final long id=counter++;
	public Composing(Share share) {
		System.out.println("creating "+this);
		this.share=share;
		// 增加一次引用
		this.share.addRef();
	}
	protected void dispose() {
		System.out.println("disposing "+this);
		share.dispose();
	}
	public String toString() {
		return "Composing "+id ;
	}
}