package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value= 'Login']");
	private By forgottenpwdLink = By.xpath("//div[@class='form-group']/a[text()= 'Forgotten Password']");//Register
	private By RegisterLink = By.linkText("Register");
	//create a constructor of page and pass driver
	
	public LoginPage(WebDriver driver) {
		System.out.println("loginpage constructor");
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println(title);
		return title;
	}
	
	public String getLoginPageurl() {
		String url = eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME);
		return url;
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("===username:"+ username + " ===");
		System.out.println("===password:"+ pwd + " ===");
		eleUtil.doSendKeys(email, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
		
	}
	
	public boolean checkForgotPasswordLink() {
		return eleUtil.doIsDisplayed(forgottenpwdLink);
		
	}
	
	public RegistrationPage navigateToRestrationPage() {
		eleUtil.doClick(RegisterLink, TimeUtil.DEFAULT_TIME);
		return new RegistrationPage(driver);
	}
	
}
