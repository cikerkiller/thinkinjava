package com.hf.lesson17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//创建只读容器
public class ReadOnly {
	static Collection<String> data = new ArrayList<>(Countries.names(6));

	public static void main(String[] args) {
		Collection<String> c = Collections.unmodifiableCollection(new ArrayList<>(data));
		System.out.println(c);
//		c.add("one"); // 抛出异常，UnsupportedOperationException
		
		List<String> list = Collections.unmodifiableList(new ArrayList<>(data));
		System.out.println(list);
//		list.add("");// 不能修改
		
		Map<String, String> map = Collections.unmodifiableMap(new HashMap<>(Countries.capitals(5)));
		System.out.println(map);
//		map.put("", "");
		Set<String> unmodifiableSet = Collections.unmodifiableSet(new HashSet<>(data));
		System.out.println(unmodifiableSet);
//		unmodifiableSet.add("");
		
	}
}
