package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Student;
import com.sre.Topper;

@Rule(name = "A has less than 50 in maths", priority = 3)
public class StudentAHasMathsLessThanFifty {

	@Condition
	public boolean when(@Fact("studentA") Student studentA, @Fact("studentB") Student studentB) {
		return studentA.getMaths() < 50 && studentB.getMaths() > 50;
	}

	@Action
	public void then(@Fact("topper") Topper topper, @Fact("studentA") Student studentA) {
		topper.setStudent(studentA);
	}
}
