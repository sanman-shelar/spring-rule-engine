package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Student;

@Rule(name = "both has more than 50 in maths", priority = 1)
public class BothHasMathsMoreThanFiftyRule {

	@Condition
	public boolean when(@Fact("studentA") Student studentA, @Fact("studentB") Student studentB) {
		return !(studentA.getMaths() > 50 && studentB.getMaths() > 50);
	}

	@Action
	public void then() {
		System.out.println("Checking science...");
	}
}
