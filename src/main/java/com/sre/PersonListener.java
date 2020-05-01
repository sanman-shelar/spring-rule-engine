package com.sre;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.sre.rules.PersonRule;

@EnableBinding(Sink.class)
public class PersonListener {

	@StreamListener(Sink.INPUT)
	public void listen(@Payload Person person) {
		
		Facts facts = new Facts();
		facts.put("person", person);

		Rules rules = new Rules();
		rules.register(new PersonRule());

		
        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        
		System.out.println("Received from queue: " + person);
	}

}
