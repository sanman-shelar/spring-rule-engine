package com.sre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
public class StudentPublisher {

	@Autowired
	private Source source;

	@PostMapping("/student")
	public List<Student> sendMessage(@RequestBody List<Student> students) {
		source.output().send(MessageBuilder.withPayload(students).build());
		return students;
	}
}
