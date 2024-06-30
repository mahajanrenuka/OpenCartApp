package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class productInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By header = By.cssSelector("div#content h1");
	private By images = By.cssSelector("div#content a.thumbnail");
	private By addToCartButton = By.xpath("//button[text()='Add to Cart']");
	private By quantityInput = By.cssSelector("input#input-quantity");//
	private By prodInfo = By.xpath("(//div[@id= 'content']//ul[@class='list-unstyled'])[1]/li");
	private By prodPrice = By.xpath("(//div[@id= 'content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> prodInfoMap;
	
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		System.out.println("=== prod info page constructor===");
		eleUtil = new ElementUtil(driver);

}
	
	public String getHeaderText() {
		System.out.println("===1===");
		String headerText = eleUtil.doGetText(header);
		System.out.println("Header text is === " + headerText);
		return headerText;
	
	}
	
	public int getImageCount() {
		int prodImageCount = eleUtil.waitForVisibilityOfElemenetsLocated(images, TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("image count for this product is === "+ prodImageCount);
		return prodImageCount;
	}
	
	public void addPrdToCart(int quanity) {
		eleUtil.doSendKeys(quantityInput, String.valueOf(quanity));
		eleUtil.doClick(addToCartButton);
		
	}
	
	private void getProdInfo() {
		List<WebElement> prodInfoList = eleUtil.getElements(prodInfo);
		for (WebElement e : prodInfoList) {
			String prodData = e.getText();
			String allData[]= prodData.split(":");
			String dataKey=allData[0].trim();
			String dataValue=allData[1].trim();
			prodInfoMap.put(dataKey, dataValue);
		}
		System.out.println("=== prod info list size=== " +prodInfoList.size());

			
		}
		
		private void getProdPrice() {
			List<WebElement> prodPriceList = eleUtil.getElements(prodPrice);
			
				String price = prodPriceList.get(0).getText();
				String exTaxData[] = prodPriceList.get(1).getText().split(":");
				String exTaxPrice=exTaxData[1];
				prodInfoMap.put("Price", price);
				prodInfoMap.put("Ex Tax Price", exTaxPrice);
				System.out.println(price);
			
			System.out.println("=== prod info list size=== " +prodPriceList.size());
	}
		
		public Map<String, String> getAllProdInfo() {
			prodInfoMap = new LinkedHashMap<String, String>();
			prodInfoMap.put("productname", getHeaderText());
			prodInfoMap.put("productimagecount", String.valueOf(getImageCount()));
			getProdInfo();
			getProdPrice();
			return prodInfoMap;
		}
		
	
}
