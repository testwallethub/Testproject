package com.sample.configuration;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.sample.browser.Browser;
import com.sample.pages.HomePage;
import com.sample.pages.LoginPage;
import com.sample.pages.WalletHubPage;
import com.sample.utility.Utility;


public class HelperClass {

	public static WebDriver driver= Browser.getDriver();;
	static ExtentReports extent = new ExtentReports();
	ExtentTest logger;
	static ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\"+"ExtentReport.html");
	public LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	public HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	public WalletHubPage walPage = PageFactory.initElements(driver, WalletHubPage.class);
	public static Configuration config = new Configuration(); 
	public Utility util = new Utility();
	public HelperClass(){
	}

	@BeforeSuite
	public void beforeSuite(){
		
	}
	    
	@BeforeClass
	public void beforeClass(){
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethodClass(Method method){//ITestContext
		driver.manage().deleteAllCookies();
		
		Test test = method.getAnnotation(Test.class);
		extent.getStartedReporters();
		extent.attachReporter(reporter);
	    System.out.println("Method::"
	    +"::"+test.testName());
	    
	    logger=extent.createTest(test.testName());

	}

	@AfterMethod(alwaysRun=true)
	public void close(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp=Utility.getScreenshot(driver);
			
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		
		extent.flush();
	}
	
	@AfterClass
	public void afterClass(){
		
	}
	@AfterTest
	public void flush() {
		
	}
	

	@AfterSuite(alwaysRun=true)
	public void afterSuite() throws IOException{
		
		
		driver.quit();
	}
	
}