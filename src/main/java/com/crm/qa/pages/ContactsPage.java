/*Author Debasish Das*/

package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//div[@class='ui header item mb5 light-black']")
	WebElement contactsLabel;
	
	@FindBy(xpath = "//div[@class='ui header item mb5 light-black']")
	WebElement savedcontactsLabel;
		
	@FindBy(xpath = "//button[contains(text(),'New')]")
	WebElement newButton;
	
	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@name='last_name']")
	WebElement lastName;
	
	@FindBy(xpath = "//div[@name='status']")
	WebElement status;
	
/*	@FindBy(xpath = "//div[@class='item']//li[contains(text(),'New')]")
	WebElement status;*/
	
	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveButton; 
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
		+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}
	
	public void clickOnNew() {
		newButton.click();
	}
	
	public void createNewContact(String firstNameInput, String lastNameInput, String contactStatus) {

		boolean contactsLabelPresent = syncElements(driver,60,contactsLabel);
		
		if (contactsLabelPresent == Boolean.parseBoolean("True")) {
			firstName.sendKeys(firstNameInput);
			lastName.sendKeys(lastNameInput);
			System.out.println(contactStatus);
			
			status.click();
			driver.findElement(By.xpath("//div[@class='visible menu transition']//span[@class='text'][contains(text(),'New')]")).click();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			saveButton.click();			
		}
				
	}
	
	public String verifySavedContact() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return savedcontactsLabel.getText();
	}
	
	public static boolean syncElements(WebDriver driver,int timeout, WebElement locator){
		new WebDriverWait(driver,timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
		return locator.isDisplayed();
	}
}
