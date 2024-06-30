package com.pratap.orangeHRM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class HomePage {

	private WebDriver driver;
	private ExtentTest test;
	public HomePage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className = "oxd-userdropdown-name")
	private WebElement userProfileName;
	public Boolean homePageExist() {
		return userProfileName.isDisplayed();	
	}
	public String getUserProfileName() {
		if (userProfileName!=null) {
			return userProfileName.getText();
		} else {
			return "User Profile Name does not esist in the application";
		}
		
	}
	
}
