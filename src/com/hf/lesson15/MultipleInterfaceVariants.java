package com.hf.lesson15;

/**
 * 因为擦除，泛型会简化为相同的Payable,这样Hourly会重复两次实现相同的接口 移除泛型后可以编译通过
 * 
 * @author ciker
 * @desc
 *
 */
public class MultipleInterfaceVariants {

}

interface Payable<T> {
}

class Employee implements Payable {
}

class Hourly extends Employee implements Payable {
}// error: Payable接口不能多次使用不同的参数实现：Payable <Employee>和Payable <Hourly>
