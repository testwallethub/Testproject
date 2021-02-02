package com.sample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WalletHubPage extends HomePage{
	

	public WalletHubPage(WebDriver driver) {
		this.driver = driver;
		wait=new WebDriverWait(driver, 20);
	}
	
	
	@FindBy(xpath = "//*[@class=\"top-header-nav thn-guest-user\"]//*[text()=\"Login\"]")
	private WebElement loginTab;
	
	@FindBy(xpath = "//input[@placeholder=\"Email Address\"]")
	private WebElement emailAddress;
	
	@FindBy(xpath = "//input[@placeholder=\"Password\"]")
	private WebElement passwordWH;
	
	@FindBy(xpath = "//*[@class=\"toggle inline-block small\"]")
	private WebElement rememberMe;
	
	@FindBy(xpath = "//button//*[text()=\"Login\"]")
	private WebElement loginButton;
	
	String selectRatingStar = "//div[@class=\"rv review-action ng-enter-element\"]//*[@class=\"rvs-star-svg\"][%s]";
	
	String filledStars = "//div[@class=\"rv review-action ng-enter-element\"]//*[@class=\"rvs-star-svg\"][%s]//*[@fill=\"none\"]";
	
	@FindBy(xpath = "//*[@class=\"dropdown second\"]")
	private WebElement selectDropDown;
	
	String selectDropOption = "//*[@class=\"dropdown second opened\"]//*[text()=\"%s\"]";
	
	@FindBy(xpath = "//textarea[@placeholder=\"Write your review...\"]")
	private WebElement writeReview;
	
	@FindBy(xpath = "//div[text()='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[@class=\"rvc-header\"]")
	private WebElement confirmationPage;
	
	@FindBy(xpath = "//div[text()='Continue']")
	private WebElement continueButton;
	
	String textReview = "//*[text()=\"%s\"]/../../../div[@itemprop=\"description\"]";
	
	//this method is used for logging in to the application
	public void login(String username,String password) {
		loginTab.click();
		emailAddress.sendKeys(username);
		passwordWH.sendKeys(password);
		rememberMe.click();
		loginButton.click();
	}
	
	//this method is to hover on the review stars
	public void hoverOnRatingStar(String rating) {
		try {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(selectRatingStar, rating))));
		WebElement starSelection = driver.findElement(By.xpath(String.format(selectRatingStar, rating)));
		Actions action = new Actions(driver);
		action.moveToElement(starSelection).build().perform();
		}catch(NoSuchElementException e) {
			System.out.println("Web application has already been rated");
		}
	}
	
	//this method is used to select the desired star rating
	public void selectRatingStar(String rating) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(selectRatingStar, rating))));
		WebElement starSelection = driver.findElement(By.xpath(String.format(selectRatingStar, rating)));
		starSelection.click();
	}
	
	//this method is used to select desired option from the dropdown
	public void selectDropDownWriteReview(String Value,String message) {
		selectDropDown.click();
		driver.findElement(By.xpath(String.format(selectDropOption, Value))).click();
		writeReview.sendKeys(message);
		submitButton.click();
		continueButton.click();
	}
	
	// this method is used to review the text commented is exactly the same
	public String getReviewText() {
		WebElement element = driver.findElement(By.xpath(String.format(textReview, getUsername())));
		return element.getText();
	}
	
	//this method is used to get the exact username
	private String getUsername() {
		 
		return "@"+config.getUsernameWallet().substring(0, config.getUsernameWallet().indexOf("@"));
		
	}
	
	//this method is used to check if the stars are lit up or not
	public boolean starsLitUp(String rating) {
		boolean starLitUp = false;
		int n = Integer.valueOf(rating);
		for(int i=1;i<=n;i++) {
			WebElement filledStar = driver.findElement(By.xpath(String.format(filledStars, Integer.toString(i))));
			if(filledStar.isDisplayed()) {
				starLitUp = true;
			}else {
				starLitUp = false;
				break;
			}
		}
		return starLitUp;
	}
	
	
}
