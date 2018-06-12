package com.hf.lesson17.test;
// 测试参数类
public class TestParam {
	public final int size;// 容器的元素数量
	public final int loops;// 测试迭代的次数
	public TestParam(int size,int loops) {
		this.size=size;
		this.loops=loops;
	}
	public static TestParam[] array(int...values) {
		//size从数组中来[10,5000,100,5000,1000,5000,10000,500]
		int size = values.length/2;
		TestParam[] result = new TestParam[size];
		int n = 0;
		for(int i=0;i<size;i++) {
			result[i]  = new TestParam(values[n++], values[n++]);
		}
		return result;
	}
	
	public static TestParam[] array(String[] values) {
		int[] vals = new int[values.length];
		for(int i = 0; i < vals.length; i++) {
			vals[i] = Integer.decode(values[i]);
		}
		return array(vals);
	}
}
