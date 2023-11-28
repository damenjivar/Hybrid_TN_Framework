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
	public HomePage homepage;
	public RegisterPage registerpage;
	public AccountSuccessPage accountsuccesspage;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		registerpage = homepage.selectRegisterOption(); // system will redirect to RegisterPage
	}

	@Test(priority = 1)
	public void verifyRegisterWisthMandatoryFields() {
		accountsuccesspage = registerpage.registerPageMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Util.emailWithDateTimeStamp(), dataProp.getProperty("mobile"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessMessage(),
				dataProp.getProperty("accountSuccessMessage"));
	}

	@Test(priority = 2)
	public void verifyRegisterWithAllFields() {
		accountsuccesspage = registerpage.registerPageAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Util.emailWithDateTimeStamp(), dataProp.getProperty("mobile"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessMessage(),
				dataProp.getProperty("accountSuccessMessage"));
	}

	@Test(priority = 3)
	public void verifyRegisterWithExistingEmail() {
		registerpage.registerPageAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("mobile"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(registerpage.retrieveDuplicateEmailWarningMessage()
				.contains(dataProp.getProperty("duplicateEmailWarningMessage")));
	}

	@Test(priority = 4)
	public void verifyingRegisterWithMismatchPassword() {
		registerpage.registerPageAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Util.emailWithDateTimeStamp(), dataProp.getProperty("mobile"), prop.getProperty("validPassword"),
				dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(registerpage.retrieveMismatchPasswordWarningMessage()
				.contains(dataProp.getProperty("passwordMismatchWarningMessage")));
	}

	@Test(priority = 5)
	public void verifyRegisterWithNoFields() {
		registerpage.clickOnContinueButton();
		Assert.assertTrue(registerpage.retrieveAllWarningMessageStatus(
				dataProp.getProperty("privacyPolicyWarningMessage"), dataProp.getProperty("firstNameWarningMessage"),
				dataProp.getProperty("lastNameWarningMessage"), dataProp.getProperty("emailWarningMessage"),
				dataProp.getProperty("telephoneWarningMessage"), dataProp.getProperty("passwordWarningMessage")));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
