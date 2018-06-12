package com.hf.lesson14;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hf.lesson14.nullobj.Null;

/**
 * 注册工厂
 * @author ciker
 * @desc   
 *
 */
public class RegisteredFactories {
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			System.out.println(Part.createRandom());
		}
		
		// 练习13
		TypeCounter c =new TypeCounter(Part.class);
		for(int i=0;i<20;i++) {
			c.count(Part.createRandom());
		}
		System.out.println(c);		
		
		
	}
}
class Part{
	public String toString() {
		return getClass().getSimpleName();
	}
	
	static List<Class<? extends Part>> partFactories = new ArrayList<>();
	static {
		// 练习14
		partFactories.add(FuelFilter.class);
		partFactories.add(AirFilter.class);
		partFactories.add(CabinAirFilter.class);
		partFactories.add(OilFilter.class);
		partFactories.add(FanBelt.class);
		partFactories.add(PowerSteeringBelt.class);
		partFactories.add(GeneratorBelt.class);
		partFactories.add(NullBelt.class);
//		partFactories.add(new FuelFilter.Factory());
//		partFactories.add(new AirFilter.Factory());
//		partFactories.add(new CabinAirFilter.Factory());
//		partFactories.add(new OilFilter.Factory());
//		partFactories.add(new FanBelt.Factory());
//		partFactories.add(new PowerSteeringBelt.Factory());
//		partFactories.add(new GeneratorBelt.Factory());
	}
	private static Random rand = new Random(47);
	public static Part createRandom() {
		int n =  rand.nextInt(partFactories.size());
		try {
			return partFactories.get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
//		return partFactories.get(n).create();// 注册工厂
	}
}

class Filter extends Part{}

class FuelFilter extends Filter{
	public static class Factory implements com.hf.lesson14.Factory<FuelFilter>{
		@Override
		public FuelFilter create() {
			return new FuelFilter();
		}
	}
}

class AirFilter extends Filter{
	public static class Factory implements com.hf.lesson14.Factory<AirFilter>{
		@Override
		public AirFilter create() {
			return new AirFilter();
		}
	}
}
class CabinAirFilter extends Filter{
	public static class Factory implements com.hf.lesson14.Factory<CabinAirFilter>{
		@Override
		public CabinAirFilter create() {
			return new CabinAirFilter();
		}
	}
}
class OilFilter extends Filter{
	public static class Factory implements com.hf.lesson14.Factory<OilFilter>{
		@Override
		public OilFilter create() {
			return new OilFilter();
		}
	}
}




class Belt extends Part{}

class FanBelt extends Belt{
	public static class Factory implements com.hf.lesson14.Factory<FanBelt>{
		@Override
		public FanBelt create() {
			return new FanBelt();
		}
	}
}

class GeneratorBelt extends Belt{
	public static class Factory implements com.hf.lesson14.Factory<GeneratorBelt>{
		@Override
		public GeneratorBelt create() {
			return new GeneratorBelt();
		}
	}
}
class NullBelt extends Belt implements Null{
	public static class Factory implements com.hf.lesson14.Factory<NullBelt>{
		@Override
		public NullBelt create() {
			return new NullBelt();
		}
	}
}

class PowerSteeringBelt extends Belt{
	public static class Factory implements com.hf.lesson14.Factory<PowerSteeringBelt>{
		@Override
		public PowerSteeringBelt create() {
			return new PowerSteeringBelt();
		}
	}
}