package com.sre;

import lombok.Data;

@Data
public class Person {

	private String name;
	private int age;
	private boolean isSenior = false;
}
