package com.pratap.orangeHRM.base;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class BrowserActions{

	private WebDriver driver;
	private ExtentTest test;
	public BrowserActions(WebDriver driver, ExtentTest test) {	
		this.driver = driver;
		this.test = test;
	}
	public void click(WebElement element) {
		try {
			
			if (element.isEnabled()) {
				//String screenshotpath=captureScreenshot("Click on Element");
				test.info("Click on element",MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(), "Element").build());
				element.click();
			} else {
				//String screenshotpath=captureScreenshot("Element Disbled");
				test.fail("Element is disabled",MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(), "Element").build());
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//String screenshotpath=captureScreenshot("Click_Failed");
			test.fail("Failed to click on element",MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(), "Application").build());
			test.fail(e.getMessage());
		}
		
		
	}
	public void type(WebElement element,String text) {
		try {
			element.sendKeys(text);
			//String screenshotpath=captureScreenshot("Type");
			//test.info("Typed text: "+text).addScreenCaptureFromPath(screenshotpath);
			//test.info( "Typed text: "+text,MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());

			test.info( "Typed text: "+text,MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
		} catch (Exception e) {
			// TODO: handle exception
			//String screenshotpath=captureScreenshot("Type_Failed");
			test.fail("Failed to type text: "+text,MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
			test.fail(e.getMessage());
		}
		
		
	}
	public String captureScreenshot(String actionName){
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String dest=System.getProperty("user.dir")+"/screenshots/"+actionName+" - "+System.currentTimeMillis()+".png";
		
		try {
			Files.copy(src.toPath(), Paths.get(dest));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			test.info(e.getMessage());
		}
		return dest;
	}
	public String captureScreenshot(){
		TakesScreenshot ts=(TakesScreenshot) driver;
		String base64code=ts.getScreenshotAs(OutputType.BASE64);		
		return base64code;
	}
	
}
