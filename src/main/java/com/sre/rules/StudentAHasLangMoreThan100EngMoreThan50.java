package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Student;
import com.sre.Topper;

@Rule(name = "Student A Has Lang More Than 100 Eng More Than 50", priority = 2)
public class StudentAHasLangMoreThan100EngMoreThan50 {

	@Condition
	public boolean when(@Fact("studentA") Student studentA, @Fact("studentB") Student studentB) {
		return studentA.getLang() > 100 && studentA.getEnglish() < 50 && studentB.getLang() > 100
				&& studentB.getEnglish() > 50;
	}

	@Action
	public void then(@Fact("topper") Topper topper, @Fact("studentA") Student studentA) {
		topper.setStudent(studentA);
	}
}
