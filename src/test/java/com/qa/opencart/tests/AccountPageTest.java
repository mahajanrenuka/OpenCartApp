package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Checking page header")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-104")
	@Step("Registeri page title")
	@Test
	public void accountsPageTitleExist() {
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}
	
	@Description("Checking page header")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-103")
	@Step("Registeri page header")
	@Test
	public void accountPageHeaderTest() {
		
		Assert.assertEquals(accountsPage.getHeaders(), AppConstants.ACCOUNT_PAGE_HEADER_LIST, AppErrors.LIST_NOT_MATCHING);
	}
	
	@Test
	public boolean searchExistTest(String searchKey) {
		return accountsPage.checkSearchBoxExists();
		
	}
//	@Test
//	public void search() throws InterruptedException {
//		accountsPage.doSearch("macbook");
//	}
	
	@DataProvider
	public Object [][] getSearchData() {
		return new Object [][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2},
			{"airtel", 0}
		};
			}
	@Description("Checking prod search result")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-102")
	@Step("Registeri new users on registration page using user id:   {0}")
	@Test(dataProvider="getSearchData")
	public void resCountTest(String searchkey, int count) {
		resPage = accountsPage.doSearch(searchkey);
		//System.out.println(resPage.resultCountTest());
		Assert.assertEquals(resPage.resultCountTest(), count, AppErrors.COUNT_NOT_MATCHING);
	}
	
	}

