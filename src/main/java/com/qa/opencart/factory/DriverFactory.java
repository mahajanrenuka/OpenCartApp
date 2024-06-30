package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.BrowserException;

/*
 * This method is used to initialize driver on the basis of driver name provided
 */

public class DriverFactory {
	
	
	WebDriver driver;
	protected	Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	OptionsManager optionsManager;
	public static String highlight;
	
	public WebDriver initDriver(Properties prop) {
		
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		
		System.out.println("Browser name is "+  browser);
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println(prop +" is not a valid browser");
			throw new BrowserException(AppErrors.BROWSER_NOT_FOUND);
			
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);
		System.out.println("page launched");
		return getDriver();
		
	}
	
	/*
	 * get local thread copy of driver
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/*
	 * this method is used to initialize properties
	 */
	public Properties initProp()  {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		if (envName==null) {
		System.out.println("===No env provided. Hence running tests on QA env===");
		try {
			ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}	
		else {
		try {
			System.out.println("=====running test on the ===" + envName + " environment===");
			switch (envName.trim().toLowerCase()){
			case "qa":
			ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			break;
			case "dev":
			ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
			break;
			case "stage":
			ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
			break;
			case "uat":
			ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
			break;
			case "prod":
			ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			break;
			default:
			break;
			} 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
			try {
				prop.load(ip);
				System.out.println("===properties loaded=====");
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
	
		return prop;
	}
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp location
		String path = System.getProperty("user.dir")+"/Screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return path;
	}
}

