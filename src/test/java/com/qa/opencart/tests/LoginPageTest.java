package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.listeners.ExtentReportListener;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

@Listeners({ExtentReportListener.class})
public class LoginPageTest extends BaseTest{
	
	
	WebDriver driver;

	@Description("Checking page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-103")
	@Step("login page title")
	
	@Test(priority=1)
	public void getPageTitle() {	
	String actTitle =loginPage.getLoginPageTitle();
	System.out.println(actTitle);
	Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
		
	}

	@Description("get page url")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-103")
	@Step("login page header")
	@Test(priority=2)
	public void getPageUrl() {
	String actUrl =loginPage.getLoginPageurl();
	System.out.println(actUrl);
	Assert.assertTrue(actUrl.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION), AppErrors.URL_NOT_FOUND);
}
	@Test(priority=3)
	public void checkForgotPasswordLink() {
		
		Assert.assertTrue(loginPage.checkForgotPasswordLink(), AppErrors.LINK_NOT_FOUND);//(true);
	}
	
	@Description("Checking page header")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-103")
	@Step("Login functionality")
	@Test(priority=3)
	public void login() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}
}
