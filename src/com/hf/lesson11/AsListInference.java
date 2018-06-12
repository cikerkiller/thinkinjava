package com.hf.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AsListInference {
	public static void main(String[] args) {
		List<Snow> snow1=Arrays.asList(new Crusty(),new Slush(),new Powder());
		// 
		List<Snow> snow2 = Arrays.<Snow>asList(new Light(),new Heavy());// 显示类型参数说明
		//List<Show> snow21 = Arrays.asList(new Light(),new Heavy()); 不能编译成功
		List<Snow> list=new ArrayList<>();
		Collections.addAll(list, new Light(),new Heavy());
	}
}

class Snow{}
class Powder extends Snow{}
class Light extends Powder{}
class Heavy extends Powder{}
class Crusty extends Snow{}
class Slush extends Snow{}
