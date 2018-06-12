package com.hf.lesson09;

public class FilterProcessor {
	public static void main(String[] args) {
		Waveform waveform=new Waveform();
		Apply.process(new FilterAdapter(new LowPass(1.0)) , waveform);
		Apply.process(new FilterAdapter(new HighOass(2.3)) , waveform);
		Apply.process(new FilterAdapter(new BandPass(1.0,2.3)) , waveform);
	}
}
