package helper;

import org.openqa.selenium.WebDriver;

public class DriverProvider {
	private static WebDriver driver;
	
	public static void setDriver(WebDriver newDriver) {
		driver = newDriver;
	}
	public static WebDriver getDriver() {
		return driver;
	}
	
}
