package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchProductPage;

public class SearchProductTest extends TestBase {

	public SearchProductTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public HomePage homepage;
	public SearchProductPage searchproductpage;

	@BeforeMethod
	public void searchProductSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		homepage = new HomePage(driver);
		homepage.enterValidProductNameInSearchBoxField(dataProp.getProperty("validProduct"));
		searchproductpage = homepage.clickOnSearchButton(); // this will redirect to SearchPage
		Assert.assertTrue(searchproductpage.displayStatusOfValidProduct());
	}

	@Test(priority = 2)
	public void VerifySearchWithInvalidProduct() {
		homepage = new HomePage(driver);
		homepage.enterValidProductNameInSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchproductpage = homepage.clickOnSearchButton();
		searchproductpage.displayStatusOfInvalidProduct();
	}

	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		homepage = new HomePage(driver);
		searchproductpage = homepage.clickOnSearchButton();
		searchproductpage.displayStatusOfInvalidProduct();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}