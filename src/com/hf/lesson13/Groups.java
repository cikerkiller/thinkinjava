package com.hf.lesson13;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Groups 分组 A(B(C))D 0:ABCD 1:BC 2:C
 * @author ciker
 * @desc   
 *
 */
public class Groups {
	static public final String POEM = "This is test\n i love you \n\n abc is abccba\n yes or no is big question";
	
	public static void main(String[] args) {
		// 模式标记(?m) find第一次循环是This is test 第一组 以一个或多个非空白符开头，后面跟着空白+ 加上（以一个或多个非空白符开头，后面跟着空白+以一个或多个非空白符结尾）
		Matcher matcher = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
//		while(matcher.find()) {
//			for(int i=0;i<=matcher.groupCount();i++) {
//				System.out.print("["+matcher.group(i)+"]");
//			}
//			System.out.println();  
//		}
//		
		matcher =Pattern.compile("\\W").matcher(POEM);
//		while(matcher.find()) {
//			System.out.print("m: "+matcher.group());
//			System.out.println();
//		}
		
		List<String> list = Arrays.asList(POEM.split("\\W"));
		for(String s:list) {
			matcher=Pattern.compile("^[a-z]",Pattern.CASE_INSENSITIVE).matcher(s);
			if(matcher.find()) {
				System.out.println(s);  
			}
		}
		
	}
}
