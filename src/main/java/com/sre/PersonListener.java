package com.sre;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.sre.rules.PersonAgeRule;
import com.sre.rules.PersonNameRule;

@EnableBinding(Sink.class)
public class PersonListener {

	@Autowired
	private RulesEngine rulesEngine;
	
	@StreamListener(Sink.INPUT)
	public void listen(@Payload Person person) {

		Facts facts = new Facts();
		facts.put("person", person);

		Rules rules = new Rules();
		rules.register(new PersonNameRule());
		rules.register(new PersonAgeRule());
		
		rulesEngine.fire(rules, facts);

		System.out.println("Received from queue: " + person);
	}

}
