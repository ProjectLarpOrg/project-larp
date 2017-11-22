package org.projectlarp.test.cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.Duration;
import org.joda.time.Instant;

import cucumber.api.java.en.Then;

public class CucumberHookTimeoutFailure {

	private Instant t1 = Instant.now();

	@Deprecated
	@Then("test duration should be less than (\\d+) seconds")
	public void test_duration(Integer expected) {
		Instant t2 = Instant.now();
		Duration duration = new Duration(t1.getMillis(), t2.getMillis());
		long s = duration.getStandardSeconds();
		assertThat(s).isLessThan(expected);
	}
}
