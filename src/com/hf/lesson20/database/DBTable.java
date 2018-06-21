package com.hf.lesson20.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用于类上，可以指定需要处理器创建数据库表的表名
 * @author ciker
 * @desc   
 *
 */
@Target(ElementType.TYPE)// 注解只能用于类上
@Retention(RetentionPolicy.RUNTIME)// VM在运行时也保留注解，，所以可以用反射获取到注解的信息
public @interface DBTable {
	public String name() default "";  
}
