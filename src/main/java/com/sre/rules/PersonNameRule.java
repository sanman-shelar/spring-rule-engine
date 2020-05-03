package com.sre.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.sre.Person;

@Rule(name = "Person Name rule", description = "Set full name for John :P", priority = 2)
public class PersonNameRule {

	@Condition
	public boolean isOlder(@Fact("person") Person person) {
		return person.getName().equalsIgnoreCase("John");
	}

	@Action
	public void setSeniority(@Fact("person") Person person) {
		person.setName(person.getName().concat(" Doe"));
	}
}
