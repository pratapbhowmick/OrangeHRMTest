package com.pratap.orangeHRM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class LoginPage {

	private WebDriver driver;
	private ExtentTest test;
	public LoginPage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "username")
	private WebElement usernameField;
	@FindBy(name = "password")
	private WebElement passwordField;
	@FindBy(xpath =   "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	public WebElement loginButton;
	
	public void enterUsername(String userName) {
		usernameField.sendKeys(userName);
		test.info("Entered username: "+userName);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
		test.info("Entered password: "+password);
	}
	public void clickLoginButton() {
		loginButton.click();
		test.info("Clicked on Login Button");
		
	}
	
}
