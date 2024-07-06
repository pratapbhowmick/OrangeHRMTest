package com.pratap.orangeHRM.base;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;

	public ConfigReader() {
		properties =new Properties();
		try {
			FileInputStream fis=new FileInputStream("src\\main\\resources\\config.properties");
			properties.load(fis);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}
