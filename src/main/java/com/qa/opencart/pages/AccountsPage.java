package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	
	private ElementUtil eleUtil;
	private WebDriver driver;
	
	private By logoutLink = By.xpath("//a[text()='Logout' and @class='list-group-item']");
	private By headers = By.cssSelector("div#content h2");
	private By name = By.name("name");
	private By search = By.name("search");
	private By searchButton = By.xpath("//div[@id='search']//button[@type = 'button']");
	

	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean checkLogoutLinkExists() {
		return eleUtil.doIsDisplayed(logoutLink);	
	}
	
	public boolean checkSearchBoxExists() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println(title);
		return title;
	}
	
	public String getAccountsPageurl() {
		eleUtil.waitForURLToBe(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME);
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public List<String> getHeaders() {
		List<WebElement> headerList = eleUtil.waitForVisibilityOfElemenetsLocated(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headerText = new ArrayList<String>();
		headerList = driver.findElements(headers);
		for (WebElement e : headerList) {
			String header = e.getText();
			headerText.add(header);
		}
		return headerText;
	}
	
	public searchResultPage doSearch(String searchKey)  {
		
		if (checkSearchBoxExists()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchButton);
			System.out.println("==== after prod search===" + searchKey);
			return new searchResultPage(driver);
		}
		else {
			System.out.println("search box not found");
			return null;
		}
		
	}
	

	

}
