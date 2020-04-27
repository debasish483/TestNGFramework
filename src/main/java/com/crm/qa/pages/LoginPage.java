/*Author Debasish Das*/

package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	//OR
	@FindBy(xpath="//span[contains(text(),'Log In')]")
	WebElement loginBtn;
	
	@FindBy(name="email")
	WebElement eMail;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	WebElement finalLoginBtn;
	
	//initialize objects
	public LoginPage( ) {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
		}
	
	public HomePage logIn(String emailAddress, String pw) {
		
		loginBtn.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		String eMailValue = eMail.getText();
		
		if (eMailValue.isEmpty() ) {
			eMail.sendKeys(emailAddress);
		}
		
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		password.sendKeys(pw);
		
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		finalLoginBtn.click();
		
		return new HomePage();
	}
}
