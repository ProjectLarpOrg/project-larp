package org.projectlarp.test.selenium;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Callable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

	// FIRST

	public static void init(WebDriver newdriver, String newbaseUrl,
			int newwaitInSeconds) {
		driver = newdriver;
		baseUrl = newbaseUrl;
		wait = newwaitInSeconds;
	}

	public static void close() {
		driver.close();
		driver = null;
		baseUrl = null;
	}

	// IMPL

	protected static WebDriver driver;
	protected static String baseUrl;
	protected static int wait;

	protected static void goTo(String relativeUrl) {
		String url = baseUrl + relativeUrl;
		driver.get(url);
	}

	protected static void waitForElement(final String cssSelector) {
		// try {
		String errorMessage = "Not at the right page. Expected current page to contains: "
				+ cssSelector;
		Callable<Boolean> callable = new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				System.out.println("isAt? " + cssSelector);
				WebElement pageElement = null;
				try {
					pageElement = driver.findElement(By
							.cssSelector(cssSelector));
					System.out.println("yes!");
				} catch (Exception e) {
					System.out.println("nope!");
				}

				return pageElement != null;
			}
		};
		await(errorMessage) //
				.atMost(wait, SECONDS) //
				.pollInterval(2, SECONDS) //
				.until(callable);

	}

}
