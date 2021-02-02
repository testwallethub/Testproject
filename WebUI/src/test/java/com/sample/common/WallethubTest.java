package com.sample.common;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sample.configuration.HelperClass;

public class WallethubTest extends HelperClass {
	String message = "This is a test review created via a automation framework to Test, Verify and Validate if the review submitted and review in the subimitted are the same. This is to ensure that functionality is working";
	String rating = "4";

	@Test(testName = "TC1 : WalletHub Test Scenario",priority = 1)
	public void openLoginPage() throws InterruptedException {
		driver.get(config.getUrlWallet());
		Assert.assertTrue(driver.getTitle().contains("test insurance"));
		walPage.login(config.getUsernameWallet(), config.getPasswordWallet());
		walPage.hoverOnRatingStar(rating);
		Assert.assertEquals(walPage.starsLitUp(rating), true);
		walPage.selectRatingStar(rating);
		walPage.selectDropDownWriteReview("Health Insurance",message);
		Assert.assertEquals(walPage.getReviewText(), message);
		
	}
}
