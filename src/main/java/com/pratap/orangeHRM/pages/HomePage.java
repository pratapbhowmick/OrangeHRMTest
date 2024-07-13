package com.pratap.orangeHRM.pages;

import org.openqa.selenium.By;
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
	
	
	
	//List panel items
	public static By Admin=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Admin']");
	public static By PIM=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'PIM']");
	public static By Leave=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Leave']");
	public static By Time=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Time']");
	public static By Recruitment=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Recruitment']");
	public static By My_Info=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'My Info']");
	public static By Performance=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Performance']");
	public static By Dashboard=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Dashboard']");
	public static By Directory=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Directory']");
	public static By Maintenance=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Maintenance']");
	public static By Claim=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Claim']");
	public static By Buzz=By.xpath("//nav[@aria-label='Sidepanel']//span[text() = 'Buzz']");
	
	public static By topBarHeader=By.xpath("//header/div[1]/div[1]/span[1]");
	public Boolean homePageExist() {
		return userProfileName.isDisplayed();	
	}
	
	public String getTopBarHeaderTitle() {
		return driver.findElement(topBarHeader).getText();
	}
	public String getUserProfileName() {
		if (userProfileName!=null) {
			return userProfileName.getText();
		} else {
			return "User Profile Name does not esist in the application";
		}
		
	}
	
}
