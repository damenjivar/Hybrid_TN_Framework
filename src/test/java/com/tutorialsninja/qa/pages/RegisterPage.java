package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;

	@FindBy(xpath = "//fieldset[@id = 'account']/descendant::input[@name = 'firstname']")
	private WebElement firstNameTextboxField;

	@FindBy(xpath = "//fieldset[@id = 'account']/descendant::input[@name = 'lastname']")
	private WebElement lastNameTextboxField;

	@FindBy(xpath = "//fieldset[@id = 'account']/descendant::input[@name = 'email']")
	private WebElement emailTextboxField;

	@FindBy(xpath = "//aside[@id = 'column-right']/preceding::input[@id = 'input-telephone']")
	private WebElement mobileTextboxField;

	@FindBy(xpath = "//input[@name = 'telephone']/following::input[@name = 'password']")
	private WebElement passwordTextboxField;

	@FindBy(xpath = "//input[@name = 'telephone']/following::input[@name = 'confirm']")
	private WebElement confirmPasswordTextboxField;

	@FindBy(xpath = "//div[@class = 'pull-right']/child::a[1]/following-sibling::input[@name = 'agree']")
	private WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//div[@class = 'pull-right']/descendant::input[@class = 'btn btn-primary']")
	private WebElement continueButton;

	@FindBy(xpath = "//aside[@id = 'column-right']/preceding::input[@name = 'newsletter' and @value = '1']")
	private WebElement newsletterRadioButton;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;

	@FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div[1]")
	private WebElement firstNameWarningMessage;

	@FindBy(xpath = "//input[@id = 'input-lastname']/following-sibling::div[1]")
	private WebElement lastNameWarningMessage;

	@FindBy(xpath = "//input[@id = 'input-email']/following-sibling::div[1]")
	private WebElement emailWarningMessage;

	@FindBy(xpath = "//input[@id = 'input-telephone']/following-sibling::div[1]")
	private WebElement telephoneWarningMessage;

	@FindBy(xpath = "//input[@id = 'input-password']/following-sibling::div[1]")
	private WebElement passwordWarningMessage;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateWarningMessage;

	@FindBy(xpath = "//input[@id = 'input-confirm']/following-sibling::div[1]")
	private WebElement mismatchPasswordWarningMessage;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public AccountSuccessPage registerPageMandatoryFields(String firstNameText, String lastNameText, String emailText,
			String mobileText, String passwordText, String confirmPasswordText) {
		firstNameTextboxField.sendKeys(firstNameText);
		lastNameTextboxField.sendKeys(lastNameText);
		emailTextboxField.sendKeys(emailText);
		mobileTextboxField.sendKeys(mobileText);
		passwordTextboxField.sendKeys(passwordText);
		confirmPasswordTextboxField.sendKeys(confirmPasswordText);
		privacyPolicyCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage registerPageAllFields(String firstNameText, String lastNameText, String emailText,
			String mobileText, String passwordText, String confirmPasswordText) {
		firstNameTextboxField.sendKeys(firstNameText);
		lastNameTextboxField.sendKeys(lastNameText);
		emailTextboxField.sendKeys(emailText);
		mobileTextboxField.sendKeys(mobileText);
		passwordTextboxField.sendKeys(passwordText);
		confirmPasswordTextboxField.sendKeys(confirmPasswordText);
		newsletterRadioButton.click();
		privacyPolicyCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void enterFirstNameInFirstNameTextboxField(String firstNameText) {
		firstNameTextboxField.sendKeys(firstNameText);
	}

	public void enterLastNameInLastNameTextboxField(String lastNameText) {
		lastNameTextboxField.sendKeys(lastNameText);
	}

	public void enterEmailInEmailTextboxField(String emailText) {
		emailTextboxField.sendKeys(emailText);
	}

	public void enterMobileInMobileTextboxField(String mobileText) {
		mobileTextboxField.sendKeys(mobileText);
	}

	public void enterPasswordInPasswordTextboxField(String passwordText) {
		passwordTextboxField.sendKeys(passwordText);
	}

	public void enterPasswordInConfirmPasswordTextboxField(String confirmPasswordText) {
		confirmPasswordTextboxField.sendKeys(confirmPasswordText);
	}

	public void clickOnPrivacyPolicyCheckbox() {
		privacyPolicyCheckbox.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesForNewsletterRadioButton() {
		newsletterRadioButton.click();
	}

	public String retrievePrivacyPolicyWarningMessage() {
		String privacypolicywarningmessage = privacyPolicyWarningMessage.getText();
		return privacypolicywarningmessage;
	}

	public String retrieveFirstNameWarningMessage() {
		String firstnamewarningmessage = firstNameWarningMessage.getText();
		return firstnamewarningmessage;
	}

	public String retrieveLastNameWarningMessage() {
		String lastnamewarningmessage = lastNameWarningMessage.getText();
		return lastnamewarningmessage;
	}

	public String retrieveEmailWarningMessage() {
		String emailwarningmessage = emailWarningMessage.getText();
		return emailwarningmessage;
	}

	public String retrieveTelephoneWarningMessage() {
		String telephonewarningmessage = telephoneWarningMessage.getText();
		return telephonewarningmessage;
	}

	public String retrievePasswordWarningMessage() {
		String passwordwarningmessage = passwordWarningMessage.getText();
		return passwordwarningmessage;
	}

	public String retrieveDuplicateEmailWarningMessage() {
		String duplicatewarningmessage = duplicateWarningMessage.getText();
		return duplicatewarningmessage;
	}

	public void enterMismatchPasswordInConfirmPasswordTextboxField(String invalidPassword) {
		confirmPasswordTextboxField.sendKeys(invalidPassword);
	}

	public String retrieveMismatchPasswordWarningMessage() {
		String mismatchpasswordwarningmessage = mismatchPasswordWarningMessage.getText();
		return mismatchpasswordwarningmessage;
	}

	public boolean retrieveAllWarningMessageStatus(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,
			String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning,
			String expectedPasswordWarning) {
		boolean privacyPolicyWarningStatus = privacyPolicyWarningMessage.getText().contains(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus = firstNameWarningMessage.getText().contains(expectedFirstNameWarning);
		boolean lastNameWarningStatus = lastNameWarningMessage.getText().contains(expectedLastNameWarning);
		boolean emailWarningStatus = emailWarningMessage.getText().contains(expectedEmailWarning);
		boolean telephoneWarningStatus = telephoneWarningMessage.getText().contains(expectedTelephoneWarning);
		boolean passwordWarningStatus = passwordWarningMessage.getText().contains(expectedPasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;

	}

}
