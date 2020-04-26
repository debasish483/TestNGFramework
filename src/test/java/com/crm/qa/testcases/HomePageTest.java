package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();	
		homePage = loginpage.logIn(prop.getProperty("username"), prop.getProperty("password"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void homePageTitleTest() {
		String homePageTitle = homePage.validateHomepageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRM");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void validateLoggedInUserTest() {
		String loggedInUser = homePage.validateLoggedInUser();
		Assert.assertEquals(loggedInUser, "Deb Das");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=3)
	public void navigateToContactsPage() {
		contactsPage = homePage.clickContactsLink();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "expected contacts label is missing");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
