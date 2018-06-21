package com.hf.lesson19;

public interface Competitor<T extends Competitor<T>>{
	Outcome compete(T competitor);
}
