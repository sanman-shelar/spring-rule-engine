package com.sre;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.sre.rules.PersonAgeRule;
import com.sre.rules.PersonNameRule;

@EnableBinding(Sink.class)
public class PersonListener {

	@StreamListener(Sink.INPUT)
	public void listen(@Payload Person person) {

		Facts facts = new Facts();
		facts.put("person", person);

		Rules rules = new Rules();
		rules.register(new PersonNameRule());
		rules.register(new PersonAgeRule());

		// rule engine parameters
		RulesEngineParameters parameters = new RulesEngineParameters()
				.skipOnFirstAppliedRule(true)
				.skipOnFirstFailedRule(true);

		// create a rules engine and fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
		rulesEngine.fire(rules, facts);

		System.out.println("Received from queue: " + person);
	}

}
