package ru.mail.bm.at.drivers;


import net.thucydides.core.webdriver.DriverSource;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.junit.*;
import org.openqa.selenium.WebDriver;



public class ChromeATDriver implements DriverSource 
{

	public String driver_name = "";
	
	@Override
	 public WebDriver newDriver() {
		
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("--ignore-certificate-errors");
		// options.addArguments("--window-size=100,100");
		 options.addArguments("--test-type");
		
		 WebDriver driver;
		 System.setProperty("webdriver.chrome.driver",  "src\\test\\resources\\chromedriver.exe");
		 driver = new ChromeDriver(options);
		 driver.manage().window().maximize();

		 driver_name = "chrome";
		 System.out.println("BRAUSER" + driver_name);

		 return driver;
	 }
	 
	public boolean takesScreenshots() 
	{
		return true;
	}
}
