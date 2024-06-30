package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	public RegistrationPage (WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	//locators
			By continueBtn = By.linkText("Continue");
			By firstName = By.id("input-firstname");
			By lastName = By.id("input-lastname");
			By emailAddress = By.id("input-email");
			By telephone = By.id("input-telephone");
			By password = By.id("input-password");
			By passwordConfirm = By.id("input-confirm");
			By agreeCheck = By.name("agree");
			By submitBtn = By.className("btn-primary");//("btn btn-primary");
			By successAlert = By.xpath("//div[@id='content']/h1");
			By postSubmitH1 = By.className("content");
			By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
			By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
			By logoutBtn = By.linkText("Logout");
			By registerLink = By.linkText("Register");
			
			public boolean userRegister(String firstName, String lastName, String emailAddress, String telephone, String password, 
					String subscibeChoice) {
				eleUtil.doSendKeys(this.firstName, firstName, TimeUtil.DEFAULT_TIME);
				eleUtil.doSendKeys(this.lastName, lastName);
				eleUtil.doSendKeys(this.emailAddress, emailAddress);
				eleUtil.doSendKeys(this.telephone, telephone);
				eleUtil.doSendKeys(this.password, password);
				eleUtil.doSendKeys(this.passwordConfirm, password);
				if (subscibeChoice.trim().equalsIgnoreCase("yes")){
					eleUtil.doClick(subscribeYes);
				}
				else if (subscibeChoice.trim().equalsIgnoreCase("no")){
					eleUtil.doClick(subscribeNo);
					
				}
				
				eleUtil.doClick(agreeCheck);
				eleUtil.doClick(submitBtn);
				String successMsg = eleUtil.waitForElementVisible(successAlert, TimeUtil.DEFAULT_MEDIUM_TIME).getText();
				System.out.println(successMsg);
				if (successMsg.contains(AppConstants.REGISTRATION_SUCCESS_MESSAGE)){
					eleUtil.doClick(logoutBtn);
					eleUtil.doClick(registerLink);
					return true;
				}
				else {
					return false;
				}
			}
}
