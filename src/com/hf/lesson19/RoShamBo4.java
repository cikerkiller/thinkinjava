package com.hf.lesson19;

public enum RoShamBo4 implements Competitor<RoShamBo4>{
	
	ROCK{
		@Override
		public Outcome compete(RoShamBo4 opponnent) {
			return compete(SCISSORS,opponnent);
		}
	},
	SCISSORS{

		@Override
		public Outcome compete(RoShamBo4 opponnent) {
			return compete(PAPER,opponnent);
		}
		
	},
	
	PAPER{

		@Override
		public Outcome compete(RoShamBo4 opponnent) {
			return compete(ROCK,opponnent);
		}
		
	};
	
	@Deprecated
	Outcome compete(RoShamBo4 loser, RoShamBo4 opponnent) {
		return ((opponnent == this) ? Outcome.DRAW : ((opponnent == loser)? Outcome.WIN : Outcome.LOSE));
	}
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo4.class, 20);
	}
}
