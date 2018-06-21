package com.hf.lesson19;

public enum Outcome {
	WIN("赢"), LOSE("输"), DRAW("平局");
	private String desc;
	Outcome(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
