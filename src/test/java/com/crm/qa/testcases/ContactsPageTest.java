/*Author Debasish Das*/

package com.crm.qa.testcases;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	String sheetName = "Contacts";
	
	//Logger log = Logger.getLogger(ContactsPageTest.class);
	
	public ContactsPageTest() {
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
			//log.warn("");
		}
		contactsPage = homePage.clickContactsLink();
		
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getCRMTestData")
	public void createContacts(String firstNameInput, String lastNameInput, String contactStatus) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		Assert.assertTrue(contactsPage.verifyContactsLabel());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		contactsPage.clickOnNew();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		contactsPage.createNewContact(firstNameInput, lastNameInput, contactStatus);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		String savedContactsName = contactsPage.verifySavedContact();
		
		String fullName = firstNameInput + " " + lastNameInput;
		Assert.assertEquals(savedContactsName, fullName, "Expected contact " +  fullName + " not saved properly");
	}
	
	@AfterMethod
	public void tearDown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		driver.quit();
	}
}
