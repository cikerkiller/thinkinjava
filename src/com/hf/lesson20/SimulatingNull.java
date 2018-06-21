package com.hf.lesson20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认值限制，对于非基本类型元素，不能以null作为值，而且要么具有默认值，要么使用注解时提供元素的值
 * @author ciker
 * @desc   
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {
	public int id() default -1;
	public String description() default "";
}
