package com.sample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends HomePage{
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait=new WebDriverWait(driver, 20);
	}
	
	
	@FindBy(xpath = "//input[@id=\"email\"]")
	public WebElement usernameInput;
	
	@FindBy(xpath = "//input[@id=\"pass\"]")
	public WebElement passwordInput;
	
	@FindBy(xpath = "//button[@type=\"submit\"]")
	public WebElement submitButton;
	
	@FindBy(xpath = "//*[@aria-label=\"Account\"]")
	private WebElement logoutIcon;
	
	@FindBy(xpath = "//span[text()=\"Log Out\"]")
	private WebElement logoutButton;
	
	
	
	//this method is used to get url
	public void getURL(String url) {
		driver.get(url);
	}
	
	//this method is used for login
	public void login(String username,String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();
		wait.until(ExpectedConditions.urlContains("welcome"));
	}
	public void inValidlogin(String username,String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();
		
	}
	//this method is used for logout
	public void logout() {
		logoutIcon.click();
		logoutButton.click();
	}
	
}
