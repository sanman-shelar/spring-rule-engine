package com.sre;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

@EnableBinding(Sink.class)
public class PersonListener {

	@StreamListener(Sink.INPUT)
	public void listen(@Payload Person person) {

		NameValueReferableMap<Person> facts = new FactMap<>();
		facts.setValue("person", person);

		// create a rules engine and fire rules on known facts
		RuleBookRunner ruleBook = new RuleBookRunner("com.sre.rules");
		ruleBook.run(facts);

		System.out.println("Received from queue: " + person);
	}

}
