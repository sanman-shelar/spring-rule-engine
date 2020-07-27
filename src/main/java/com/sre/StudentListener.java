package com.sre;

import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.jeasy.rules.support.composite.ConditionalRuleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.sre.rules.BothHasMathsLessThanFiftyRule;
import com.sre.rules.BothHasMathsMoreThanFiftyRule;
import com.sre.rules.BothHasScienceMoreThanFiftyRule;
import com.sre.rules.StudentAHasLangLessThan100EngLessThan50;
import com.sre.rules.StudentAHasLangLessThan100EngMoreThan50;
import com.sre.rules.StudentAHasLangMoreThan100EngLessThan50;
import com.sre.rules.StudentAHasLangMoreThan100EngMoreThan50;
import com.sre.rules.StudentAHasMathsLessThanFifty;
import com.sre.rules.StudentAHasMathsMoreThanFifty;
import com.sre.rules.StudentAHasScienceLessThanFifty;
import com.sre.rules.StudentAHasScienceMoreThanFifty;

@EnableBinding(Sink.class)
public class StudentListener {

	@Autowired
	private RulesEngine rulesEngine;

	@StreamListener(Sink.INPUT)
	public void listen(@Payload List<Student> students) {

		Topper topper = new Topper();

		Facts facts = new Facts();
		facts.put("studentA", students.get(0));
		facts.put("studentB", students.get(1));
		facts.put("topper", topper);

		ActivationRuleGroup mathsSubGroup = new ActivationRuleGroup("maths subgroup", "", 2);
		mathsSubGroup.addRule(new StudentAHasMathsMoreThanFifty());
		mathsSubGroup.addRule(new StudentAHasMathsLessThanFifty());
		mathsSubGroup.addRule(new BothHasMathsLessThanFiftyRule());

		ConditionalRuleGroup maths = new ConditionalRuleGroup("maths", "", 1);
		maths.addRule(new BothHasMathsMoreThanFiftyRule());
		maths.addRule(mathsSubGroup);

		ActivationRuleGroup science = new ActivationRuleGroup("science", "", 2);
		science.addRule(new BothHasScienceMoreThanFiftyRule());
		science.addRule(new StudentAHasScienceMoreThanFifty());
		science.addRule(new StudentAHasScienceLessThanFifty());

		ActivationRuleGroup language = new ActivationRuleGroup("lang", "", 3);
		language.addRule(new StudentAHasLangMoreThan100EngLessThan50());
		language.addRule(new StudentAHasLangMoreThan100EngMoreThan50());
		language.addRule(new StudentAHasLangLessThan100EngMoreThan50());
		language.addRule(new StudentAHasLangLessThan100EngLessThan50());

		Rules rules = new Rules();
		rules.register(maths);
		rules.register(science);
		rules.register(language);

		rulesEngine.fire(rules, facts);

		System.out.println("Received from queue: " + students);
		System.out.println("Topper: " + topper);
	}

}
