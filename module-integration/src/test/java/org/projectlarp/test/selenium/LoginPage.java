package org.projectlarp.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {
	
	// NAVIGATION

	public static void goTo() {
		goTo("#/auth/login");
	}

	public static void isAt() {
		waitForElement("#login");
	}

	// MODEL
	
	private static void fillUsername(String str) {
		WebElement el = driver.findElement(By.cssSelector("[name=\"username\"]"));
		el.sendKeys(str);
	}

	private static void fillPassword(String str) {
		WebElement el = driver.findElement(By.cssSelector("[name=\"password\"]"));
		el.sendKeys(str);
	}

	private static void clickLogin() {
		WebElement el = driver.findElement(By.cssSelector("form#login button"));
		el.click();
	}
	
	// BUSINESS
	
	public static void doLogon(String username, String password) {
		goTo();
		isAt();
		fillUsername(username);
		fillPassword(password);
		clickLogin();
	}



}
