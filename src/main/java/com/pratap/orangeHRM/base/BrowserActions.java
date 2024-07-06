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
import com.pratap.orangeHRM.utils.ExtentManager;

public class BrowserActions{
	;
	private WebDriver driver;
	private ExtentTest test;
	private String screenshotType;
	public BrowserActions(WebDriver driver, ExtentTest test) {	
		this.driver = driver;
		this.test = test;
		ConfigReader congConfigReader=new ConfigReader();
		this.screenshotType=congConfigReader.getProperty("ScreenshotType");
	}
	public void click(WebElement element) {
		try {
			
			if (element.isEnabled()) {
				//String screenshotpath=captureScreenshot("Click on Element");
				//test.info("Click on element",MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot(), "Element").build());
				addScreenshot("Click on element",element.getText());
				element.click();
			} else {
				//String screenshotpath=captureScreenshot("Element Disbled");
				
				test.fail("Element is disabled");
				addScreenshot("Element is disabled",element.getText());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//String screenshotpath=captureScreenshot("Click_Failed");
			test.fail("Failed to click on element");
			addScreenshot("Failed to click on element");
			test.fail(e.getMessage());
		}
		
		
	}
	public void type(WebElement element,String text) {
		try {
			element.sendKeys(text);
			//String screenshotpath=captureScreenshot("Type");
			//test.info("Typed text: "+text).addScreenCaptureFromPath(screenshotpath);
			//test.info( "Typed text: "+text,MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());

			test.info( "Typed text- "+text);
			addScreenshot("Typed text- "+text,"Type");
		} catch (Exception e) {
			// TODO: handle exception
			//String screenshotpath=captureScreenshot("Type_Failed");
			test.fail("Failed to type text- "+text);
			addScreenshot("Failed to type text- "+text,"Type");
			test.fail(e.getMessage());
		}
		
		
	}
	private void addScreenshot(String actionName) {
		if ("Base64".equalsIgnoreCase(screenshotType)) {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromBase64String(captureBase64Screenshot()).build());
		} else {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(actionName)).build());
		}
	}
	public void addScreenshot(String actionName,String title) {
		
		if ("Base64".equalsIgnoreCase(screenshotType)) {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromBase64String(captureBase64Screenshot(),title).build());
		} else {
			test.info(actionName,MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(actionName),title).build());
		}
	}
	public String captureScreenshot(String actionName){
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
	public String captureBase64Screenshot(){
		TakesScreenshot ts=(TakesScreenshot) driver;
		String base64code=ts.getScreenshotAs(OutputType.BASE64);		
		return base64code;
	}
	
}
