package com.pratap.orangeHRM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.pratap.orangeHRM.base.BrowserActions;

public class LoginPage {

	private WebDriver driver;
	private ExtentTest test;
	private BrowserActions actions;
	public LoginPage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		this.actions=new BrowserActions(driver, test);
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "username")
	private WebElement usernameField;
	@FindBy(name = "password")
	private WebElement passwordField;
	@FindBy(xpath =   "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	public WebElement loginButton;
	
	public void enterUsername(String userName) {
		actions.type(usernameField, userName);
	}
	
	public void enterPassword(String password) {
		actions.type(passwordField, password);
	}
	public void clickLoginButton() {
		actions.click(loginButton);
		
	}
	
}
