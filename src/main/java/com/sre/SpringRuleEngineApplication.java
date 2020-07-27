package com.sre;

import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRuleEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRuleEngineApplication.class, args);
	}

	@Bean
	public RulesEngine rulesEngine() {
		// rule engine parameters
		RulesEngineParameters parameters = new RulesEngineParameters()
				.skipOnFirstAppliedRule(true)
				.skipOnFirstFailedRule(true);

		// create a rules engine and fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
		return rulesEngine;
	}
}
