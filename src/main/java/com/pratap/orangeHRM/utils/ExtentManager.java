package com.pratap.orangeHRM.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;
	private static String reportDir;
	public static ExtentReports createInstance(String filename) {
		String timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss").format(LocalDateTime.now()); 
		reportDir=System.getProperty("user.dir")+"/Reports/"+timeStamp;
		new File(reportDir).mkdirs();
		ExtentSparkReporter reporter=new ExtentSparkReporter(reportDir+"/"+filename);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle("Automation Test");
		reporter.config().setEncoding("utf-8");
		reporter.config().setReportName("Automation Test Results");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pratap");
		return extent;
	}
	public static ExtentReports getInstance() {
		// TODO Auto-generated method stub
		if (extent==null) {
			//String timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss").format(LocalDateTime.now());  
			  
			extent=createInstance("ExtentReports.html");
		} 
		return extent;
	}
	public static String getReportDir() {
		return reportDir;
	}
}
