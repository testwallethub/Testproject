package com.sample.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sample.configuration.Configuration;
import com.sample.constants.Constants;

public class Browser {
	static Configuration config = new Configuration();
	public static WebDriver driver = Constants.driver;
	static Constants constants;
	
	public static WebDriver getDriver() {
		if(config.getBrowser().equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", constants.driversPath+"chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("--js-flags=--expose-gc");  
			options.addArguments("--enable-precise-memory-info"); 
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("test-type=browser");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}
		return driver;
	}

}
