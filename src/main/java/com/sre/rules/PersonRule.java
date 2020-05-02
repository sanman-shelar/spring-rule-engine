package com.sre.rules;

import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import com.sre.Person;

@Rule(order = 1)
public class PersonRule {

	@Given("person")
	private Person person;

	@When
	public boolean isOlder() {
		return person.getAge() > 60;
	}

	@Then
	public void setSeniority() {
		person.setSenior(true);
	}
}
