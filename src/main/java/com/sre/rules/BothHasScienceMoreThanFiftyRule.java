package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Student;
import com.sre.Topper;

@Rule(name = "both has more than 50 in science", priority = 1)
public class BothHasScienceMoreThanFiftyRule {

	@Condition
	public boolean when(@Fact("studentA") Student studentA, @Fact("studentB") Student studentB) {
		return studentA.getScience() > 50 && studentB.getScience() > 50;
	}

	@Action
	public void then(@Fact("topper") Topper topper, @Fact("studentA") Student studentA) {
		topper.setStudent(studentA);
	}
}
