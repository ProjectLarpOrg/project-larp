package org.projectlarp.test.selenium;

public class HomePage extends AbstractPage {
	
	// NAVIGATION

	public static void goTo() {
		goTo("#/home");
	}

	public static void isAt() {
		waitForElement("[ng-controller=\"DashboardCtrl\"]");
	}

}
