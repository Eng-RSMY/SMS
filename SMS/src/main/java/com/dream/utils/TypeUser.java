package com.dream.utils;

public enum TypeUser {

	HM(1), Teacher(2), Attender(3), Parent(4), Student(5);
	private int value;

	TypeUser(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
