package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Person;

@Rule(name = "Person rule", description = "Set seniority value based on age")
public class PersonRule {

	@Condition
	public boolean isOlder(@Fact("person") Person person) {
		return person.getAge() > 60;
	}

	@Action
	public void setSeniority(@Fact("person") Person person) {
		person.setSenior(true);
	}
}
