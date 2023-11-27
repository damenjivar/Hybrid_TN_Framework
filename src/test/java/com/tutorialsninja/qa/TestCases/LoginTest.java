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

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		homepage.selectLoginOption();
	}

	@Test(priority = 1, dataProvider = "TNLogin", dataProviderClass = ExcelCode.class)
	public void verifyLoginWithValidCredentials(String username, String password) {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextboxField(username);
		loginpage.enterPasswordinPasswordTextboxField(password);
		loginpage.clickOnLoginButton();
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.verifyLoginLinkIsDisplayed());
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidEmailValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextboxField(Util.emailWithDateTimeStamp());
		loginpage.enterPasswordinPasswordTextboxField(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage)); // deliberate fail
	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextboxField(prop.getProperty("validEmail"));
		loginpage.enterPasswordinPasswordTextboxField(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextboxField(Util.emailWithDateTimeStamp());
		loginpage.enterPasswordinPasswordTextboxField(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
