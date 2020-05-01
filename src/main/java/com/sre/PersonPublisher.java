package com.sre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
public class PersonPublisher {
	
	@Autowired
    private Source source;

	@PostMapping("/person")
	public Person sendMessage(@RequestBody Person person) {
		source.output().send(MessageBuilder.withPayload(person).build());
		return person;
	}
}
