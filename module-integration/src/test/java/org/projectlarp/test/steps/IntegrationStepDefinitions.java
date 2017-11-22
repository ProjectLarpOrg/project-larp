package org.projectlarp.test.steps;

import org.projectlarp.test.util.HTTPConnection;
import org.projectlarp.test.util.PropertiesLoader;
import cucumber.api.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Glue between text based scenarios and Java modules.
 */
public class IntegrationStepDefinitions {

	private PropertiesLoader props = new PropertiesLoader("env.properties");

	@Given("^nothing$")
	public void nothing() throws Throwable {
		// NOTHING TO DO
	}

	@When("^SYSTEM is up$")
	public void system_is_up() throws Throwable {
		// NOTHING TO DO HERE
	}

	@Then("^(.*) is up and running with code=(.*)$")
	public void sdm_is_up_and_running_with_code(String name, String code)
			throws Throwable {
		String key = name.toLowerCase() + ".check.url";
		String url = props.getProperty(key);
		int httpCode = HTTPConnection.getHTTPResponseCodeFrom(url);
		int expected = Integer.valueOf(code).intValue();
		assertThat(httpCode) //
			.overridingErrorMessage("Expected httpCode to be <%s> but was <%s> from URL <%s>", expected, httpCode, url) //
			.isEqualTo(expected);
	}

}
