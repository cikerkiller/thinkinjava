package com.hf.lesson17;

import java.util.Collections;

public class Synchronizeation {
	public static void main(String[] args) {
		// 创建同步集合
		Collections.synchronizedCollection(ReadOnly.data);
	}
}
