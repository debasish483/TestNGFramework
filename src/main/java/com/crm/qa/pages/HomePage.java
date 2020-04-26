package com.crm.qa.pages;

//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;*/

import com.crm.qa.base.TestBase;
//import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase {
	
	//OR
	@FindBy(xpath="//span[@class='user-display']")
	WebElement userNameLabel;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//span[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	//initialize objects
	public HomePage() {
		PageFactory.initElements(driver, this);
		
	}

	//Actions
	
	public String validateHomepageTitle() {
		return driver.getTitle();
	}
	
	public String validateLoggedInUser() {
		//driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return userNameLabel.getText();
	}
	
	public ContactsPage clickContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
/*	public static void syncElements(WebDriver driver,int timeout, WebElement locator){
		new WebDriverWait(driver,timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}*/
	
	
}
