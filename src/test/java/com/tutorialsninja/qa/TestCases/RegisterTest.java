package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.Utilities.Util;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends TestBase {

	public RegisterTest() throws Exception {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		homepage.selectRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisterWisthMandatoryFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextboxField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameInLastNameTextboxField(dataProp.getProperty("lastName"));
		registerpage.enterEmailInEmailTextboxField(Util.emailWithDateTimeStamp());
		registerpage.enterMobileInMobileTextboxField(dataProp.getProperty("mobile"));
		registerpage.enterPasswordInPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.enterPasswordInConfirmPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyCheckbox();
		registerpage.clickOnContinueButton();
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String actualSuccessMessage = accountsuccesspage.retrieveAccountSuccessMessage();
		String expectedSuccessMessage = dataProp.getProperty("accountSuccessMessage");
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
	}

	@Test(priority = 2)
	public void verifyRegisterWithAllFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextboxField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameInLastNameTextboxField(dataProp.getProperty("lastName"));
		registerpage.enterEmailInEmailTextboxField(Util.emailWithDateTimeStamp());
		registerpage.enterMobileInMobileTextboxField(dataProp.getProperty("mobile"));
		registerpage.enterPasswordInPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.enterPasswordInConfirmPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.selectYesForNewsletterRadioButton();
		registerpage.clickOnPrivacyPolicyCheckbox();
		registerpage.clickOnContinueButton();
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String actualSuccessMessage = accountsuccesspage.retrieveAccountSuccessMessage();
		String expectedSuccessMessage = dataProp.getProperty("accountSuccessMessage");
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
	}

	@Test(priority = 3)
	public void verifyRegisterWithExistingEmail() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextboxField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameInLastNameTextboxField(dataProp.getProperty("lastName"));
		registerpage.enterEmailInEmailTextboxField(prop.getProperty("validEmail"));
		registerpage.enterMobileInMobileTextboxField(dataProp.getProperty("mobile"));
		registerpage.enterPasswordInPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.enterPasswordInConfirmPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.selectYesForNewsletterRadioButton();
		registerpage.clickOnPrivacyPolicyCheckbox();
		registerpage.clickOnContinueButton();
		String actualDuplicateWarningMessage = registerpage.retrieveDuplicateEmailWarningMessage();
		String expectedDuplicateWarningMessage = dataProp.getProperty("duplicateEmailWarningMessage");
		Assert.assertTrue(actualDuplicateWarningMessage.contains(expectedDuplicateWarningMessage));
	}

	@Test(priority = 4)
	public void verifyingRegisterWithMismatchPassword() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextboxField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameInLastNameTextboxField(dataProp.getProperty("lastName"));
		registerpage.enterEmailInEmailTextboxField(Util.emailWithDateTimeStamp());
		registerpage.enterMobileInMobileTextboxField(dataProp.getProperty("mobile"));
		registerpage.enterPasswordInPasswordTextboxField(prop.getProperty("validPassword"));
		registerpage.enterMismatchPasswordInConfirmPasswordTextboxField(dataProp.getProperty("invalidPassword"));
		registerpage.clickOnPrivacyPolicyCheckbox();
		registerpage.clickOnContinueButton();

		String actualPasswordMismatchWarningMessage = registerpage.retrieveMismatchPasswordWarningMessage();
		String expectedPasswordMismatchWarningMessage = dataProp.getProperty("passwordMismatchWarningMessage");
		Assert.assertTrue(actualPasswordMismatchWarningMessage.contains(expectedPasswordMismatchWarningMessage));
	}

	@Test(priority = 5)
	public void verifyRegisterWithNoFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();

		String actualPrivacyPolicyWarningMessage = registerpage.retrievePrivacyPolicyWarningMessage();
		String expectedPrivacyPolicyWarningMessage = dataProp.getProperty("privacyPolicyWarningMessage");
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));

		String actualFirstNameWarningMessage = registerpage.retrieveFirstNameWarningMessage();
		String expectedFirstNameWarningMessage = dataProp.getProperty("firstNameWarningMessage");
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));

		String actualLastNameWarningMessage = registerpage.retrieveLastNameWarningMessage();
		String expectedLastNameWarningMessage = dataProp.getProperty("lastNameWarningMessage");
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));

		String actualEmailWarningMessage = registerpage.retrieveEmailWarningMessage();
		String expectedEmailWarningMessage = dataProp.getProperty("emailWarningMessage");
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));

		String actualTelephoneWarningMessage = registerpage.retrieveTelephoneWarningMessage();
		String expectedTelephoneWarningMessage = dataProp.getProperty("telephoneWarningMessage");
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));

		String actualPasswordWarningMessage = registerpage.retrievePasswordWarningMessage();
		String expectedPasswordWarningMessage = dataProp.getProperty("passwordWarningMessage");
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
