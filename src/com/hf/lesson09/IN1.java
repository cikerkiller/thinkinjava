package com.hf.lesson09;

public interface IN1 {
	
}
interface IN2 extends IN1{ void f();}
interface IN3 extends IN1{void g();}
interface IN4 extends IN2,IN3{
	
}
