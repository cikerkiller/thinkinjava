package com.hf.lesson09;

/**
 * 工厂方法设计模式
 * @author ciker
 * @desc   
 *
 */
public class Factories {
	public static void services(ServiceFactory factory) {
		Service service=factory.service();
		service.f();
		service.ff();
	}
	public static void main(String[] args) {
		services(new AServiceFactory());
		services(new BServiceFactory());
	}
}
interface Service{
	void f();
	void ff();
}
interface ServiceFactory{
	Service service();
}

class AService implements Service{

	@Override
	public void f() {
		System.out.println("AService.f");
	}

	@Override
	public void ff() {
		System.out.println("AService.ff");
	}
	
} 

class AServiceFactory implements ServiceFactory{
	@Override
	public Service service() {
		return new AService();
	}
}

class BService implements Service{

	@Override
	public void f() {
		System.out.println("BService.f");
	}

	@Override
	public void ff() {
		System.out.println("BService.ff");
	}
}
class BServiceFactory implements ServiceFactory{
	@Override
	public Service service() {
		return new BService();
	}
}
