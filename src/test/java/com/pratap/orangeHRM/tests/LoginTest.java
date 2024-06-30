package com.pratap.orangeHRM.tests;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pratap.orangeHRM.base.BaseTest;
import com.pratap.orangeHRM.pages.HomePage;
import com.pratap.orangeHRM.pages.LoginPage;
import com.pratap.orangeHRM.utils.ExcelUtils;

public class LoginTest extends BaseTest{
	@DataProvider(name = "loginTestData")
	public Iterator<Object[]> logInDataProvider() {
		String filePath="src\\test\\resources\\TestData.xlsx";
		String sheetName="LoginData";
		List<Object[]> testData=ExcelUtils.getTestData(filePath, sheetName);
		return testData.iterator();
	}
	
	@Test(dataProvider = "loginTestData")
	public void testLogin(String username,String password,String expLogin) {
		LoginPage loginPage=new LoginPage(driver, test);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		
		if (expLogin.equalsIgnoreCase("pass")) {
			HomePage homePage=new HomePage(driver, test);
			Assert.assertTrue(homePage.homePageExist());
		} else {
			Assert.assertTrue( loginPage.loginButton.isDisplayed());			
		}
		
	
	}

}
