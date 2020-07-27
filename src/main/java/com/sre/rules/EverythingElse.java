package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Everything else", priority = 4)
public class EverythingElse {

	@Condition
	public boolean when() {
		return true;
	}

	@Action
	public void then() {
		System.out.println("No topper found");
	}
}
