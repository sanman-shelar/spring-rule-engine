package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Person;

@Rule(name = "Person Age rule", description = "Set seniority value based on age", priority = 1)
public class PersonAgeRule {

	@Condition
	public boolean isOlder(@Fact("person") Person person) {
		return person.getAge() > 60;
	}

	@Action
	public void setSeniority(@Fact("person") Person person) {
		person.setSenior(true);
	}
}
