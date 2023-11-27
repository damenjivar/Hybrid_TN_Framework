package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;
	
	//Creation of Objects
	@FindBy(linkText = "My Account")
	private WebElement MyAccountLink;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchTextbox;
	
	@FindBy(css = "button.btn.btn-default.btn-lg")
	private WebElement searchButton;
	
	// Create the Constructor of this Page
	//Initializing the Objects
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions performed by those objects
	public void clickOnMyAccountLink() {
		MyAccountLink.click();
	}
	
	public void selectLoginOption() {
		loginOption.click();
	}
	
	public void selectRegisterOption() {
		registerOption.click();
	}

	public void enterValidProductNameInSearchBoxField(String validProductText) {
		searchTextbox.sendKeys(validProductText);
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
	}
}
