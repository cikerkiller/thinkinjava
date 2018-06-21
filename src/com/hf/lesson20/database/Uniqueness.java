package com.hf.lesson20.database;

// 唯一约束
public @interface Uniqueness {
	Constraints  constraints() default @Constraints(unique = true);
}
