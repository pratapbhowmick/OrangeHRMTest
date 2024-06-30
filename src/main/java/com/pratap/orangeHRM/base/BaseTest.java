package com.pratap.orangeHRM.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pratap.orangeHRM.utils.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		extent = ExtentManager.getInstance();
		test = extent.createTest(getClass().getSimpleName());
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus()==ITestResult.FAILURE) {
			test.fail(result.getThrowable());
		} else if(result.getStatus()==ITestResult.SUCCESS) {
			test.pass("Test Passed");
		}else {
			test.skip("Tets Skipped");
			
		}
		if (driver!=null) {
			driver.quit();
		}
		extent.flush();
	}
}
