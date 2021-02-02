package com.sample.common;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sample.configuration.HelperClass;

public class FacebookTest extends HelperClass {

	@Test(testName = "TC1 : Login Facebook Page and Update Status",priority = 1)
	public void openLoginPage() {
		driver.get(config.getUrl());
		Assert.assertTrue(driver.getTitle().contains("log in"));
		loginPage.login(config.getUsernameFB(), config.getPasswordFB());
		homePage.postStatus("Hello World");
		loginPage.logout();
	}
}
