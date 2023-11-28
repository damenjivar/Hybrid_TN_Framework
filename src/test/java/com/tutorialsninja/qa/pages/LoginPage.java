package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailTextboxField;

	@FindBy(id = "input-password")
	private WebElement passwordTextboxField;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement clickOnLoginButton;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement NoMatchPasswordWarningMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmailinEmailTextboxField(String emailText) {
		emailTextboxField.sendKeys(emailText);
	}

	public void enterPasswordinPasswordTextboxField(String passwordText) {
		passwordTextboxField.sendKeys(passwordText);
	}

	public AccountPage clickOnLoginButton() {
		clickOnLoginButton.click();
		return new AccountPage(driver);
	}

	public AccountPage navigateToLoginPage(String emailText, String passwordText) {
		emailTextboxField.sendKeys(emailText);
		passwordTextboxField.sendKeys(passwordText);
		clickOnLoginButton.click();
		return new AccountPage(driver);
	}

	public String retrieveNoMatchPasswordWarningMessage() {
		String warningMessage = NoMatchPasswordWarningMessage.getText();
		return warningMessage;
	}
}
