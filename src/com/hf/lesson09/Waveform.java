package com.hf.lesson09;

public class Waveform {
	private static long counter;
	private final long id=counter++;
	public String toString() {
		return "Waveform "+id;
	}
}

class LowPass extends Filter{
	double cutoff;

	public LowPass(double cutoff) {
		super();
		this.cutoff = cutoff;
	}

	@Override
	public Waveform process(Waveform input) {
		return input;
	}
	
}
class HighOass extends Filter{
	double cutoff;

	public HighOass(double cutoff) {
		super();
		this.cutoff = cutoff;
	}

	@Override
	public Waveform process(Waveform input) {
		return input;
	}
}

class BandPass extends Filter{
	double lowCutOff,highCutOff;

	public BandPass(double lowCutOff, double highCutOff) {
		super();
		this.lowCutOff = lowCutOff;
		this.highCutOff = highCutOff;
	}
	@Override
	public Waveform process(Waveform input) {
		return input;
	}
}

