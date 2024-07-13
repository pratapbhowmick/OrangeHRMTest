package com.pratap.orangeHRM.tests;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pratap.orangeHRM.base.BaseTest;
import com.pratap.orangeHRM.base.BrowserActions;
import com.pratap.orangeHRM.pages.HomePage;
import com.pratap.orangeHRM.pages.LoginPage;
import com.pratap.orangeHRM.utils.ExcelUtils;

public class HomepageLeftPanelNavigationTest extends BaseTest{

	@DataProvider(name = "testHomePageLeftPanel")
	public Iterator<Object[]> logInDataProvider() {
		String filePath="src\\test\\resources\\HomepageLeftPanelNavigationTest.xlsx";
		String sheetName="HomepageLeftPanelNavigationTest";
		List<Object[]> testData=ExcelUtils.getTestData(filePath, sheetName);
		return testData.iterator();
	}
	
	
	@Test(dataProvider = "testHomePageLeftPanel")
	public void testHomePageLeftPanel(String username,String password,
			String panel1,String panel2,String panel3,String panel4,String panel5,String panel6,
			String panel7,String panel8,String panel9,String panel10,String panel11,String panel12
			
			) throws InterruptedException {
		login(username, password);
		Thread.sleep(2000);
		HomePage homePage=new HomePage(driver, test);
		SoftAssert sa=new SoftAssert();
		BrowserActions actions=new BrowserActions(driver, test);
		if (!panel1.equals("")) {
			actions.click(driver.findElement(HomePage.Admin));
			
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel1);
			sa.assertTrue(actTitle.toUpperCase().contains(panel1.toUpperCase()));
		}
		if (!panel2.equals("")) {
			actions.click(driver.findElement(HomePage.PIM));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel2);
			sa.assertTrue(actTitle.toUpperCase().contains(panel2.toUpperCase()));
		}
		if (!panel3.equals("")) {
			actions.click(driver.findElement(HomePage.Leave));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel3);
			sa.assertTrue(actTitle.toUpperCase().contains(panel3.toUpperCase()));
		}if (!panel4.equals("")) {
			actions.click(driver.findElement(HomePage.Time));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel4);
			sa.assertTrue(actTitle.toUpperCase().contains(panel4.toUpperCase()));
		}if (!panel5.equals("")) {
			actions.click(driver.findElement(HomePage.Recruitment));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel5);
			sa.assertTrue(actTitle.toUpperCase().contains(panel5.toUpperCase()));
		}if (!panel6.equals("")) {
			actions.click(driver.findElement(HomePage.My_Info));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel6);
			sa.assertTrue(actTitle.toUpperCase().contains(panel6.toUpperCase()));
		}if (!panel7.equals("")) {
			actions.click(driver.findElement(HomePage.Performance));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel7);
			sa.assertTrue(actTitle.toUpperCase().contains(panel7.toUpperCase()));
		}if (!panel8.equals("")) {
			actions.click(driver.findElement(HomePage.Dashboard));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel8);
			sa.assertTrue(actTitle.toUpperCase().contains(panel8.toUpperCase()));
		}if (!panel9.equals("")) {
			actions.click(driver.findElement(HomePage.Directory));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel9);
			sa.assertTrue(actTitle.toUpperCase().contains(panel9.toUpperCase()));
		}if (!panel10.equals("")) {
			actions.click(driver.findElement(HomePage.Maintenance));
			waitForSecond(6);
			
			WebElement AdminPasswordPrompt=driver.findElement(By.name("password"));
			if (AdminPasswordPrompt.isDisplayed()) {
				AdminPasswordPrompt.sendKeys(password);
				actions.click(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/form[1]/div[4]/button[2]")));
				waitForSecond(3);
				
			}
			
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel10);
			sa.assertTrue(actTitle.toUpperCase().contains(panel10.toUpperCase()));
		}if (!panel11.equals("")) {
			actions.click(driver.findElement(HomePage.Claim));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel11);
			sa.assertTrue(actTitle.toUpperCase().contains(panel11.toUpperCase()));
		}if (!panel12.equals("")) {
			actions.click(driver.findElement(HomePage.Buzz));
			waitForSecond(3);
			String actTitle=homePage.getTopBarHeaderTitle();
			addScreenshot(panel12);
			sa.assertTrue(actTitle.toUpperCase().contains(panel12.toUpperCase()));
		}
		sa.assertAll();
		
	
	}
}
