package org.projectlarp.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * For debug purpose only !
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true, //
features = { //
		"features", //
		"../module-specs/features" }, //
format = { "pretty", "html:target/cucumber-html", "json:target/cucumber.json" }, // REPORTS
tags = { "~@ignore", "~@major", "@run" })
public class RunTagsOnlyTest {

}
