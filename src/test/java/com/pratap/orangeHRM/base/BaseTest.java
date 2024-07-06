package com.pratap.orangeHRM.base;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.pratap.orangeHRM.utils.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;
	private ConfigReader configReader;
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser,ITestContext context) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else {
			//Default browser as chrome
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		configReader=new ConfigReader();
		
		driver.get(configReader.getProperty("BaseURL"));
		
		extent = ExtentManager.getInstance();
		test = extent.createTest(getClass().getSimpleName() +" - "+browser); //getClass().getSimpleName() //context.getName()
		File screenshotDir=new File(ExtentManager.getReportDir()+"/screenshots/");
		if (!screenshotDir.exists()) {
			screenshotDir.mkdir();
		}
//		File extentReportsDir=new File(System.getProperty("user.dir")+"/ExtentReports/");
//		if (!extentReportsDir.exists()) {
//			extentReportsDir.mkdir();
//		}
		
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
	public void addScreenshot(String actionName) {
		
		if ("Base64".equalsIgnoreCase(configReader.getProperty("ScreenshotType"))) {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromBase64String(captureBase64Screenshot()).build());
		} else {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(actionName)).build());
		}
	}
	public void addScreenshot(String actionName,String title) {
		
		if ("Base64".equalsIgnoreCase(configReader.getProperty("ScreenshotType"))) {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromBase64String(captureBase64Screenshot(),title).build());
		} else {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(actionName),title).build());
		}
	}
	private String captureScreenshot(String actionName){
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String dest=ExtentManager.getReportDir()+"/screenshots/"+actionName+" - "+System.currentTimeMillis()+".png";
		
		try {
			Files.copy(src.toPath(), Paths.get(dest));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			test.info(e.getMessage());
		}
		return dest;
	}
	private String captureBase64Screenshot(){
		TakesScreenshot ts=(TakesScreenshot) driver;
		String base64code=ts.getScreenshotAs(OutputType.BASE64);		
		return base64code;
	}
}
