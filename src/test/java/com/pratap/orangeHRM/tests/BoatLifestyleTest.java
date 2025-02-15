package com.pratap.orangeHRM.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BoatLifestyleTest {
	@Test
	public void test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.boat-lifestyle.com/");
		Thread.sleep(5000);
		System.out.println("Startig hovering");
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/store-header/div[1]/div/nav/desktop-navigation/ul/li[1]/a/span")));
		//Thread.sleep(1000);
		actions.click( driver.findElement(By.xpath("//*[@id=\"desktop-menu-1\"]/li[5]/a/p")));
		actions.build().perform();
	}

}
