package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
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

	@BeforeMethod
	public void searchProductSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterValidProductNameInSearchBoxField(dataProp.getProperty("validProduct"));
		homepage.clickOnSearchButton();
		SearchProductPage searchproductpage = new SearchProductPage(driver);
		searchproductpage.displayStatusOfValidProduct();
	}

	@Test(priority = 2)
	public void VerifySearchWithInvalidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterValidProductNameInSearchBoxField(dataProp.getProperty("invalidProduct"));
		homepage.clickOnSearchButton();
		SearchProductPage searchproductpage = new SearchProductPage(driver);
		searchproductpage.displayStatusOfInvalidProduct();
	}

	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.clickOnSearchButton();
		SearchProductPage searchproductpage = new SearchProductPage(driver);
		searchproductpage.displayStatusOfInvalidProduct();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}