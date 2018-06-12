package com.hf.lesson03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 扔硬币
 * @author ciker
 * @desc   
 *
 */
public class Coin {
	public static void main(String[] args) throws Exception {
//		for(;;) {
//			coinTest();
//		}
//		bitTest();
//		operators();
//		primeNumber();
//		resultSequence(2);
		vampireFigures(4);
	}
	
	public static void coinTest() {
		Random random=new Random();
		int coin=random.nextInt(2);
		System.out.println(coin);
	}
	
	/**
	 * 按位操作符测试方法
	 */
	public static void bitTest() {
		int a=2;
		int b=1;
		int c=0;
		System.out.println(a&b);
		System.out.println(a&c);
		System.out.println(a|b);
		System.out.println(a|c);
	}
	public static void operators() {
		int a=2;
		int b=1;
		int c=0;
		String d="ff";
		System.out.println(a+b+c+d);
		System.out.println(a+b+d+c);
		System.out.println(a+d+b+c);
		System.out.println(d+a+b+c);
		boolean ff=true;
		boolean fg=true;
		boolean fh=false;
		System.out.println(ff&fg);
		System.out.println(ff&fh);
		System.out.println(ff|fg);
		System.out.println(ff|fh);
		System.out.println(ff=fg);
		System.out.println(ff=fh);
		
		float fl=29.7f;
		a=(int) fl;
		System.out.println(a);
		
	}
	
	/**
	 * 输出素数
	 */
	public static void primeNumber() {
		List<Integer> list=new ArrayList<>();
		label1:
		for(int i=2;i<100;i++) {
//			if(isPrime(i)) {
//				System.out.println(i);
//				list.add(i);
//				
//			}
			
			if(i>1&&i<=3) {
				System.out.println(i);
				list.add(i);
				continue;
			}
			
			for(int j=2;j<i;j++) {
				if(i%j==0) {
					continue label1;
				}
			}
			System.out.println(i);
			list.add(i);
			
		}
		System.out.println(list.size());
	}
	
	/**
	 * 输入一个数判断是否是素数
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n) {
		if(n<=3) {
			// 0,1,2,3
			return n>1;
		}
		
		for(int i=2;i<n;i++) {
			// 能够被i整除说明不是素数
			if(n%i==0) {
				return false;
			}
		}
		return true;
	}
	
	
	public static Integer sequence(Integer n) throws Exception {
		if(n<0) {
			throw new Exception("n Can't be less than 0");
		}
		if(n==0||n==1) {
			return 1;
		}
		return sequence(n-2)+sequence(n-1);
	}
	
	/**
	 * 裴波拉契数列
	 * @param n 数列最大不超过n的值的数列
	 * @throws Exception
	 */
	public static void resultSequence(int n) throws Exception {
		int a;
		for(int i=1;;) {
			a=sequence(i);
			if(a>n) {
				break;
			}
			i++;
			System.out.println(a);
		}
	}
	
	/**
	 * 吸血鬼数字 1260=21*60
	 * @param n 位数
	 * @throws Exception 
	 */
	public static void vampireFigures(int n) throws Exception {
		if(n==0||n%2!=0) {
			throw new Exception("n not be even");
		}
//		int nn=Double.valueOf(Math.pow(10, n)).intValue();
//		int m=Double.valueOf(Math.pow(10, n-1)).intValue();
//		for(int i=m;i<nn;i++) {
//			char[] nums=String.valueOf(i).toCharArray();
//			num(i, nums);
//		}
		
		// 逆向思维
		String[] targetNum;
		String[] nums;
		int sum=1;
		for(int i=10;i<100;i++) {
			for(int j=i+1;j<100;j++) {
				int target=i*j;
				if(target<1000||target>10000) {
					continue;
				}
				targetNum=String.valueOf(target).split("");
				nums=(String.valueOf(i)+String.valueOf(j)).split("");
				Arrays.sort(targetNum);
				Arrays.sort(nums);
//				System.out.println(sum++);
				if(Arrays.equals(targetNum, nums)) {
					System.out.println("i="+i+",j="+j+",target="+target);
				}
			}
		}
		
		
		
	}
	/**
	 * 吸血鬼数字
	 * @param n
	 * @param nums
	 * @throws Exception
	 */
	public static void num(int n,char[] nums) throws Exception {
		// 1234  12 13 14 3
		int length=nums.length;
		label1:
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(i==j) {
					continue;
				}
				for(int k=0;k<length;k++) {
					if(k==j||k==i) {
						continue;
					}
					for(int h=0;h<length;h++) {
						if(h==j||h==i||h==k) {
							continue;
						}
						int aa=Integer.valueOf(nums[i]+""+nums[j]).intValue();
						int bb=Integer.valueOf(nums[k]+""+nums[h]).intValue();
						if(n==aa*bb) {
							System.out.println("aa="+aa+",bb="+bb+",n="+n);
							continue label1;
						}
					}
				}
			}
		}
	}
	
	
	
}
