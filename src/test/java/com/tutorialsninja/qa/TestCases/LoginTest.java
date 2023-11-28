package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.TestData.ExcelCode;
import com.tutorialsninja.qa.Utilities.Util;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LoginTest extends TestBase {

	public LoginTest() throws Exception {
		super();

	}

	public WebDriver driver;
	public LoginPage loginpage;
	public HomePage homepage;
	public AccountPage accountpage;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		loginpage = homepage.selectLoginOption(); // this returns a new LoginPage
	}

	@Test(priority = 1, dataProvider = "TNLogin", dataProviderClass = ExcelCode.class)
	public void verifyLoginWithValidCredentials(String username, String password) {
		accountpage = loginpage.navigateToLoginPage(username, password);
		Assert.assertTrue(accountpage.verifyLoginLinkIsDisplayed());
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidEmailValidPassword() {
		loginpage.navigateToLoginPage(Util.emailWithDateTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginpage.retrieveNoMatchPasswordWarningMessage()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailInvalidPassword() {
		loginpage.navigateToLoginPage(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveNoMatchPasswordWarningMessage()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidCredentials() {
		loginpage.navigateToLoginPage(Util.emailWithDateTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveNoMatchPasswordWarningMessage()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {
		loginpage.clickOnLoginButton();
		Assert.assertTrue(loginpage.retrieveNoMatchPasswordWarningMessage()
				.contains(dataProp.getProperty("emailPasswordNoMatchWarningMessage")));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
