package org.projectlarp.test.cucumber;

import java.util.Collection;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Fail all future scenarios if one of the important scenario has failed. But
 * still execute all important scenarios.
 */
public class CucumberHookWantsToQuitOnMajorFailure {

	private static final String MAJOR_TAG = "@major";
	private static boolean prevScenarioFailed = false;

	@After
	public void watch_this_major_scenario(Scenario scenario) throws Exception {
		if (isMajor(scenario)) {
			boolean isFailed = scenario.isFailed();
			if (isFailed)
				prevScenarioFailed = isFailed;
		}
	}

	@Before
	public void quit_if_major_scenario_failed(Scenario scenario) {
		if (!isMajor(scenario) && prevScenarioFailed) {
			throw new IllegalStateException(
					"An important scenario has failed before! Cucumber Wants to quit...");
		}
	}

	private boolean isMajor(Scenario scenario) {
		Collection<String> tags = scenario.getSourceTagNames();
		return tags.contains(MAJOR_TAG);
	}

}
