package com.hf.lesson14;


/**
 * RTTI run-time type identification 运行时类型识别  主要两种方式
 * 传统方式： 1、向上向下转型
 * 		  2、Class的cast()
 * 		  3、instanceof or isInstance
 * 反射：Java不允许在运行时改变程序结构或类型变量的结构，但它允许在运行时去探知、加载、调用在编译期完全未知的class，可以在运行时加载该class，生成实例对象（instance object），调用method，或对field赋值。
 * 		这种类似于“看透”了class的特性被称为反射（Reflection）
 * 反射缺陷：性能，通过反射调用方法的效率比直接调用的效率要至少慢一倍以上。
 * 优点：开发的灵活性
 * 区别：在于传统RTTI在编译期需要.class文件，而反射不需要。传统的RTTI使用转型或Instance形式实现，但都需要指定要转型的类型
 * @author ciker
 * @desc   
 *
 */
public class RTTITest {   
	public static void main(String[] args) {
	}
}
