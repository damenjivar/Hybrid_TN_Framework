package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {

	public WebDriver driver;

	@FindBy(id = "button-cart")
	private WebElement addToCartButtonInsideProductInfo;

	@FindBy(xpath = "//li[contains(text(), 'Product Code:Product 21')]")
	private WebElement productCodeInfoDisplayed;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnAddToCartButtonInsideProductInfo() {
		addToCartButtonInsideProductInfo.click();
	}

	public boolean validateDisplayStatusProductInfo() {
		boolean displayStatus = productCodeInfoDisplayed.isDisplayed();
		return displayStatus;
	}

}
