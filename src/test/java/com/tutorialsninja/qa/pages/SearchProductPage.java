package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductPage {

	public WebDriver driver;

	@FindBy(linkText = "HP LP3065")
	private WebElement validProduct;

	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement noProductMatchCriteria;
	
	@FindBy(xpath = "//div[@class = 'caption']/following-sibling::div/child::button[1]")
	private WebElement addToCartButton;

	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean displayStatusOfValidProduct() {
		boolean validDisplay = validProduct.isDisplayed();
		return validDisplay;
	}

	public boolean displayStatusOfInvalidProduct() {
		boolean invalidDisplay = noProductMatchCriteria.isDisplayed();
		return invalidDisplay;
	}
	
	public ProductInfoPage clickOnAddToCartButton() {
		addToCartButton.click();
		return new ProductInfoPage(driver);
	}
	
	
}
