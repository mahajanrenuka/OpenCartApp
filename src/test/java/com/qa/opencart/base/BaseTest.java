package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.searchResultPage;
import com.qa.opencart.pages.productInfoPage;


public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected searchResultPage resPage;
	protected productInfoPage prdInfoPage;
	protected RegistrationPage regPage;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("firefox") String browsername) {

		df = new DriverFactory();
		prop = df.initProp();
		if (browsername !=null) {
			prop.setProperty("browser", browsername);
		}
		driver=df.initDriver(prop);//This is call by reference. prop is a ref variables passed as argument in initDriver method
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
