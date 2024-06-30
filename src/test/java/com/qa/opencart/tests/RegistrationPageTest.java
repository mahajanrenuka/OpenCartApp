package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.StringUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void navigateToRegpage() {
	regPage = loginPage.navigateToRestrationPage();
	}
	//(String firstName, String lastName, String emailAddress, String telephone, String password, String subscibeChoice)
	@Description("Checking user registration form")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Renuka M")
	@Issue("NQRM-101")
	@Test(priority=1)
	@Step("Registeri new users on registration page using user id:   {3}")
	@DataProvider
	public Object [][] userRegistrationData(){
		return new Object[][]{
			{"John", "Smith4", "1234567891", "johnsmith101", "yes"},//removed to fail test case
			{"John", "Snith5", "1234567891", "johnsmith101", "no1"}
			};
		
	}
	@Test(dataProvider="userRegistrationData")
	public void userRegistration(String firstname, String lastname, String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.userRegister(firstname, lastname, StringUtils.generateRandomEmail(), telephone, password, subscribe));
	}

}
//autouser1718992747271@opencart.com
//Your Account Has Been Created!
//autouser1718992751938@opencart.com