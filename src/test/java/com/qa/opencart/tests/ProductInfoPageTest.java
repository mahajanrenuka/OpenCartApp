package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object [][] getProductData() {
		return new Object [][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung Galaxy Tab 10.1"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"canon", "Canon EOS 5D"}
		};
			}
	
	
	@Description("Checking prod page header")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-103")
	@Step("Registeri page login page title")
	@Test(dataProvider= "getProductData")
 	public void checkProdPageHeader(String searchKey, String productName)  {
		resPage = accountsPage.doSearch(searchKey);
		prdInfoPage = resPage.selectProduct(productName);
		Assert.assertEquals(prdInfoPage.getHeaderText(), "MacBook Pro", AppErrors.HEADER_DOES_NOT_MATCH);
 		
 	}
	

	@DataProvider
	public Object [][] getProductImageData() {
		return new Object [][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 10},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"canon", "Canon EOS 5D", 3}
		};
			}
	
	@Test(dataProvider="getProductImageData")
 	public void getProdPImageCount(String searchKey, String productName, int imgCount)  {
		resPage = accountsPage.doSearch(searchKey);
		prdInfoPage = resPage.selectProduct(productName);
		Assert.assertEquals(prdInfoPage.getImageCount(), imgCount, AppErrors.IMAGE_COUNT_DOES_NOT_MATCH);
 		
 	}
	
	@DataProvider
	public Object [][] addProdToCartData() {
		return new Object [][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"canon", "Canon EOS 5D", 3}
		};
	}
	@Description("Checking prod details for:  {1}")
	@Severity(SeverityLevel.MINOR)
	@Owner("Renuka M")
	@Issue("NQRM-103")
	@Step("check prod detail info")
	@Test(dataProvider="addProdToCartData")
 	public void addProdToCart(String searchKey, String productName, int quantity)  {
		resPage = accountsPage.doSearch(searchKey);
		prdInfoPage = resPage.selectProduct(productName);
		prdInfoPage.addPrdToCart(quantity);
 		
 	}
	//{productname=MacBook Pro, productimagecount=4, Brand=Apple, 
	//Product Code=Product 18, Reward Points=800, 
	//Availability=In Stock, Price=$2,000.00, Ex Tax Price= $2,000.00}
	@Test
	public void getProdInfo() throws InterruptedException {
		resPage = accountsPage.doSearch("macbook");
		prdInfoPage = resPage.selectProduct("MacBook Pro");
		Map<String, String> productInfo =  prdInfoPage.getAllProdInfo();
		System.out.println(productInfo);
		softAssert.assertEquals(productInfo.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfo.get("productimagecount"), "4");
		softAssert.assertEquals(productInfo.get("Brand"), "Apple");
		softAssert.assertEquals(productInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfo.get("Reward Points"), "800");
		softAssert.assertEquals(productInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfo.get("Price"), "$2,000.00");
		softAssert.assertEquals(productInfo.get("Ex Tax Price"), " $2,000.00");
		softAssert.assertAll();
		
	}

}
