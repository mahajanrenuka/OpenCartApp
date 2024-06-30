package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class searchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResult = By.cssSelector("div.product-thumb");
	
	public  searchResultPage(WebDriver driver) {
		this.driver = driver;
		System.out.println("===search res page constructor====" );
		eleUtil = new ElementUtil(driver);
	}
	
	
	public int resultCountTest() {
		List<WebElement> resList= eleUtil.waitForVisibilityOfElemenetsLocated(searchResult, TimeUtil.DEFAULT_LONG_TIME);
		System.out.println("result count is === "+ resList.size());
		return resList.size();
	}
	
	public productInfoPage selectProduct(String productName) {
		System.out.println("===before select prod====" + productName);
		eleUtil.doClick(By.linkText(productName), TimeUtil.DEFAULT_TIME);
		return new productInfoPage(driver);
	
	}
	
	

}
