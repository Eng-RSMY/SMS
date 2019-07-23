package com.dream.utils;

public enum Roles {

	HM(1), Teacher(2), Attender(3), Parent(4), Student(5);
	private int value;

	Roles(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
