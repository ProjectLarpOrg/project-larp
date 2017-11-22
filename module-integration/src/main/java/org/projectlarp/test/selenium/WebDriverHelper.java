package org.projectlarp.test.selenium;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHelper {

	public static WebDriver init() {
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, SECONDS);
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1024, 768));
		return driver;
	}

}
