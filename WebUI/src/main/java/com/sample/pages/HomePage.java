package com.sample.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sample.configuration.Configuration;

public class HomePage {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor executor;
	public static Configuration config = new Configuration(); 
	public HomePage(){
		executor = (JavascriptExecutor)driver;
	}
	
	@FindBy(xpath = "//*[@aria-label=\"Home\"]")
	private WebElement homeButton;
	
	@FindBy(xpath = "//*[@aria-label=\"Create a post\"]//div[@role=\"button\"]")
	private WebElement createPostButton;
	
	@FindBy(xpath = "//*[@role=\"dialog\"]//*[@role=\"textbox\"]")
	private WebElement createPostText;
	
	@FindBy(xpath = "//span[contains(text(),'Post')]")
	private WebElement postButton;
	
	//this method is used to post desired status
	public void postStatus(String postText){
		homeButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(createPostButton));
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", createPostButton);
		createPostText.sendKeys(postText);
		postButton.click();
	}
}
