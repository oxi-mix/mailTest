package ru.mail.bm.at.drivers;

import java.io.File;

import net.thucydides.core.webdriver.DriverSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class FirefoxATDriver implements DriverSource {

	@Override
	public WebDriver newDriver() {
		
		File ffProfilePath = new File(".\\src\\test\\resources\\SeleniumFFProfile");
		
		FirefoxProfile ffProfile = new FirefoxProfile(ffProfilePath);
		
		WebDriver driver;
		driver = new FirefoxDriver(ffProfile);
		return driver;
	}

	public boolean takesScreenshots() {
		return true;
	}
}
